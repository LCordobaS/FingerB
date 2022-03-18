
package api;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.HistogramWindow;
import ij.io.Opener;
import ij.io.SaveDialog;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Api {
    
    
    
 private int matrizOperaciones[][];
   //Declaración de las dimensiones de la imagen. Ancho y alto.
     private int anchoOper=0, altoOper=0;
    //Declaración de una instancia de tipo ImagePlus para la obtención y manipulación de la imagen
    ImagePlus imagenOper;
    //Matriz para la creación de los triángulos 
    int matAux[][] = new int[512][512];
    //
    int banBit=0;
    ImageProcessor imgGuardada =null;
    ImageProcessor imgBit1 =null;
    ImageProcessor imgBit2 =null;
    ImageProcessor imgBit3 =null;
    ImageProcessor imgBit4 =null;
    ImageProcessor imgBit5 =null;
    ImageProcessor imgBit6 =null;
    ImageProcessor imgBit7 =null;
    ImageProcessor imgBit8 =null;
    //Aqui se muestra matrizResultado donde ya estan la adyacencia
     public ImageProcessor ady = null;
     public ImageProcessor rotacion;
     public ImageProcessor umbral;
     public ImageProcessor intensity;
     public ImageProcessor histo;
     public ImageProcessor expansion;
     public ImageProcessor or;
     public ImageProcessor resta;
     public ImageProcessor filtro;
     public ColorProcessor cp;
     public BufferedImage imagen;
     
    //Declaración de una instancia de tipo ImagePlus para la obtención y manipulación de la imagen
   public ImageProcessor tareaTriangulo = null;
    //Declaración de la matriz
    public int matriz[][];
    //Declaración de las dimensiones de la imagen. Ancho y alto.
    private int ancho, alto;
    //Declaracion de una instancia de tipo ImagePlus para la obtención y manipulación de la imagen
    public ImagePlus imagenO;
    int[][] matrizBinaria;
    
    private int [][] matrizRed;
    private int [][] matrizGreen;
    private int [][] matrizBlue;
   
   float aux=0, aux1=0, aux2=0, aux3=0, PR=0, PV=0, PA=0;

    //metodo que lee la imagen y la convierte a matriz
    public void leerMatriz(File ima) {
        // TODO add your handling code here:
        //Declaración de variables
        String nombre = String.valueOf(ima);
        //System.out.println("1:"+nombre);
        Opener op = new Opener();
        //en la variable imagenO se guarda la imagen obtenida
        imagenO = op.openImage(nombre);
        //se obtienes las medidas de la matriz y se guardan en Ancho Y Alto respectivamente
        ancho = imagenO.getWidth();
        alto = imagenO.getHeight();
        //Creación del objeto matriz
        matriz = new int[ancho][alto];
        imgGuardada=new ByteProcessor(ancho, alto);
        imgBit1 =new ByteProcessor(ancho, alto);
        imgBit2 =new ByteProcessor(ancho, alto);
        imgBit3 =new ByteProcessor(ancho, alto);
        imgBit4 =new ByteProcessor(ancho, alto);
        imgBit5 =new ByteProcessor(ancho, alto);
        imgBit6 =new ByteProcessor(ancho, alto);
        imgBit7 =new ByteProcessor(ancho, alto);
        imgBit8 =new ByteProcessor(ancho, alto);
        
        //Variable para la obtencion de los pixeles de la imagen
        int pixel;
        //creacion de una imagen  apartir de la obtenida en el objeto imagenO
        ImageProcessor ip = imagenO.getProcessor();
        //Los siguientes dos for se obtiene los pixeles de la imagen con getPixel y se asignan
        //a la matriz para realizar las operaciones correpondientes
        for (int a = 0; a < ancho; a++) {
            for (int b = 0; b < alto; b++) {
                pixel = ip.getPixel(a, b);
                matriz[a][b] = pixel;
            }
        }
        //con el metodo open se abre la imagen a la que se le va hacer las 
        //operaciones correpondientes solo mandando la ruta con la variable nombre
        //op.open(nombre); 
        binarizar();
        
       
        
    }

    public void hacerTriangulos(int numTriangulo) throws IOException {




        /*Método en el cual se dibuja el triángulo tomando como base esta matriz para el primer triángulo y si es más de uno se toma como base 3 y un exponente que es el número de triángulos
         0	1	2	3	4	5
         0	0	0	0	0	0	0
         1	0	0	0	255	0	0
         2	0	0	255	255	255	0

         */
        int t = (int) Math.pow(3, numTriangulo);
        int matriz[][] = new int[t][t * 2];
        int idTriangulo = 1;
        int colorTriangulo = 255;
        int colorFondo = 0;
        // variables que se utilizan para recorrer los renglones de la matriz en donde ya están los triángulos


        int esqInicial = 0;
        int esqFinal = t;
        //variables que se utilizan para recorrer las columnas de la matriz en donde se encuentran los triángulos 
        int esqInicial1 = 0;
        int esqFinal1 = t * 2;
        int indicea = 0;
        int indiceb = 0;

       // System.out.println("valor" + numTriangulo);


        //En este fragmento  de código es un ciclo en el cual se va generando cada uno de los triángulos 
        //y guardando en una matriz dependiendo de que si el número de triángulo es par o impar se le 
        //asigna el valor de 0 o 255 como color de triángulo o color de fondo.


        if (numTriangulo > 0) {
            while (numTriangulo >= idTriangulo) {
                int num = (int) Math.pow(3, numTriangulo);
                int mat[][] = new int[num][num * 2];

                //En este ciclo se llena mat que va a contener el valor de fondo o valor de triangulo que es 0 o 255
                //dependiendo del numero de triangulos
                for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num * 2; j++) {
                        if (numTriangulo % 2 == 0) {
                            colorTriangulo = 0;
                        } else {
                            colorTriangulo = 255;
                        }
                        if (colorTriangulo == 255) {
                            colorFondo = 0;
                        } else {
                            colorFondo = 255;
                        }

                        if (j < (num - (i - 1)) || (j > num + (i - 1))) {
                            mat[i][j] = colorFondo;
                        } else {

                            mat[i][j] = colorTriangulo;
                        }
                    }
                }
                int iaux = 0;


                //En este otro ciclo se vasea los valores de mat en matriz, para que que dependiendo del numero de triangulos
                //que da el usuario se disminuya el numero y pase al anterior ciclo y posteriormente vuelva a 
                //vaciarse en matriz y asi sucesivamente hasta terminar con todos los triangulos.
                for (int a = esqInicial; a < esqFinal; a++) {
                    int jaux = 0;
                    for (int b = esqInicial1; b < esqFinal1; b++) {
                        matriz[a][b] = mat[iaux][jaux];
                        jaux++;
                        indiceb = b;
                    }
                    indicea = a;
                    iaux++;
                }
                esqInicial = esqInicial + (int) Math.pow(3, numTriangulo - 1);
                esqFinal = esqFinal - (int) Math.pow(3, numTriangulo - 1);
                esqInicial1 = esqInicial1 + (((int) Math.pow(3, numTriangulo - 1)) * 2);
                esqFinal1 = esqFinal1 - ((int) Math.pow(3, numTriangulo - 1)) * 2;
                numTriangulo--;
            }
            llenaMatriz(t, indicea, indiceb, matriz);

        }

        tareaTriangulo = new ByteProcessor(512, 512);
        imgGuardada=new ByteProcessor(512, 512);
        for (int j = 0; j < 512; j++) {
            for (int i = 0; i < 512; i++) {
                tareaTriangulo.putPixel(j, i, matAux[i][j]);
                imgGuardada.putPixel(j, i, matAux[i][j]);
            }
        }
        //new ImagePlus("Triangulos", tareaTriangulo).show();
        limpiarMatriz();
    }

    //Método que se utiliza para llenar la matriz que posteriormente es pintada, se hace de esta forma:
    //De la matriz donde ya se encuentran los triángulos se divide en 4 cuadrantes y se pinta el primer cuadrante de la matriz 
    //de adentro hacia afuera y así los demás cuadrantes.
    public void llenaMatriz(int t, int indicea, int indiceb, int[][] matriz) {

        if (t * 2 <= 512) {
            int x = 256 - (t / 2);
            int y = 256 - ((t * 2) / 2);
            for (int i = 0; i < t / 2; i++) {
                y = 256 - ((t * 2) / 2);
                System.out.print("x" + x);
                for (int j = 0; j < (t * 2) / 2; j++) {
                    matAux[x][y] = matriz[i][j];
                    y++;
                }
                x++;

            }
            int x1 = 256 - (t / 2);
            int y1 = 256;
            for (int i = 0; i < t / 2; i++) {
                y1 = 256;
                for (int j = ((t * 2) / 2); j < (t * 2); j++) {
                    matAux[x1][y1] = matriz[i][j];
                    y1++;
                }
                x1++;
            }
            int x2 = 256;
            int y2 = 256 - ((t * 2) / 2);
            for (int i = (t / 2); i < t; i++) {
                y2 = 256 - ((t * 2) / 2);
                for (int j = 0; j < ((t * 2) / 2); j++) {
                    matAux[x2][y2] = matriz[i][j];
                    y2++;
                }
                x2++;
            }
            int x3 = 256;
            int y3 = 256;
            for (int i = t / 2; i < t; i++) {
                y3 = 256;
                for (int j = ((t * 2) / 2); j < (t * 2); j++) {
                    matAux[x3][y3] = matriz[i][j];
                    y3++;
                }
                x3++;
            }

        } else {
            int x = 0;
            int y = 0;
            for (int i = indicea - 256; i < indicea; i++) {
                y = 0;
                for (int j = indiceb - 256; j < indiceb; j++) {
                    matAux[x][y] = matriz[i][j];
                    y++;
                }
                x++;
            }
            int x1 = 0;
            int y1 = 0;
            for (int i = indicea - 256; i < indicea; i++) {
                y1 = y;
                for (int j = indiceb; j < indiceb + 255; j++) {
                    matAux[x1][y1] = matriz[i][j];
                    y1++;
                }
                x1++;
            }
            int x2 = x;
            int y2 = 0;
            for (int i = indicea; i < indicea + 255; i++) {
                y2 = 0;
                for (int j = indiceb - 256; j < indiceb; j++) {
                    matAux[x2][y2] = matriz[i][j];
                    y2++;
                }
                x2++;
            }
            int x3 = x;
            int y3 = 0;
            for (int i = indicea; i < indicea + 255; i++) {
                y3 = y;
                for (int j = indiceb; j < indiceb + 255; j++) {
                    matAux[x3][y3] = matriz[i][j];
                    y3++;
                }
                x3++;
            }
        }


    }
    //Método que limpia la matriz para que despues de haber elegido un numero de triangulos, y volver a elegir
    //otro numero de triangulos no se quede guardada la imagen anterior. 

    public void limpiarMatriz() {
        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                matAux[i][j] = 0;
            }
        }
    }
    //Metodo que binariza la imagen dependiendo del valor del pixel los valores menores a 128
    //se les asigna el 0 y a los otros valores 1

    public void binarizar() {
        matrizBinaria = new int[ancho + 2][alto + 2];
        int aux = 1;
        for (int a = 0; a < ancho; a++) {
            int aux2 = 1;
            for (int b = 0; b < alto; b++) {
                if (matriz[a][b] >= 128) {
                    matrizBinaria[aux][aux2] = 255;
                } else {
                    matrizBinaria[aux][aux2] = 0;
                }
                aux2++;
            }
            aux++;
        }
        /*ImageProcessor binaria =null;
         binaria =  new ByteProcessor(ancho+2,alto+2);
         for(int j=1;j<ancho+1;j++){
         for(int i=1;i<alto+1;i++){
         binaria.putPixel(j,i, matrizBinaria[j][i]);
         }
         }
         new ImagePlus("Imagen binaria",binaria).show();  */
    }

    public void adyacencia() {
        int[][] matrizAdyacencia = new int[ancho + 2][alto + 2];
        int[][] matrizResultado = new int[ancho + 2][alto + 2];
        //Este es el ejemplo visto en clase para verificar que esta bien diseñado el agoritmo
        //int [][] matrizAdyacencia=new int [5][16];
        //int [][] matrizAdyacencia1=new int [5][16];
         /*matrizAdyacencia[0][0]=0;
        
         matrizAdyacencia[0][1]=0;
         matrizAdyacencia[0][2]=0;
         matrizAdyacencia[0][3]=0;
         matrizAdyacencia[0][4]=0;
         matrizAdyacencia[0][5]=0;
         matrizAdyacencia[0][6]=0;
         matrizAdyacencia[0][7]=0;
         matrizAdyacencia[0][8]=0;
         matrizAdyacencia[0][9]=0;
         matrizAdyacencia[0][10]=0;
         matrizAdyacencia[0][11]=0;
         matrizAdyacencia[0][12]=0;
         matrizAdyacencia[0][13]=0;
         matrizAdyacencia[0][14]=0;
         matrizAdyacencia[0][15]=0;
         
         matrizAdyacencia[1][0]=0;
         matrizAdyacencia[1][1]=0;
         matrizAdyacencia[1][2]=0;
         matrizAdyacencia[1][3]=0;
         matrizAdyacencia[1][4]=0;
         matrizAdyacencia[1][5]=1;
         matrizAdyacencia[1][6]=1;
         matrizAdyacencia[1][7]=1;
         matrizAdyacencia[1][8]=0;
         matrizAdyacencia[1][9]=0;
         matrizAdyacencia[1][10]=1;
         matrizAdyacencia[1][11]=1;
         matrizAdyacencia[1][12]=0;
         matrizAdyacencia[1][13]=1;
         matrizAdyacencia[1][14]=1;
         matrizAdyacencia[1][15]=0;
         
         matrizAdyacencia[2][0]=0;
         matrizAdyacencia[2][1]=0;
         matrizAdyacencia[2][2]=0;
         matrizAdyacencia[2][3]=1;
         matrizAdyacencia[2][4]=1;
         matrizAdyacencia[2][5]=1;
         matrizAdyacencia[2][6]=0;
         matrizAdyacencia[2][7]=1;
         matrizAdyacencia[2][8]=1;
         matrizAdyacencia[2][9]=1;
         matrizAdyacencia[2][10]=1;
         matrizAdyacencia[2][11]=0;
         matrizAdyacencia[2][12]=0;
         matrizAdyacencia[2][13]=0;
         matrizAdyacencia[2][14]=1;
         matrizAdyacencia[2][15]=0;
         
         matrizAdyacencia[3][0]=0;
         matrizAdyacencia[3][1]=1;
         matrizAdyacencia[3][2]=1;
         matrizAdyacencia[3][3]=0;
         matrizAdyacencia[3][4]=0;
         matrizAdyacencia[3][5]=1;
         matrizAdyacencia[3][6]=1;
         matrizAdyacencia[3][7]=1;
         matrizAdyacencia[3][8]=0;
         matrizAdyacencia[3][9]=1;
         matrizAdyacencia[3][10]=1;
         matrizAdyacencia[3][11]=1;
         matrizAdyacencia[3][12]=1;
         matrizAdyacencia[3][13]=1;
         matrizAdyacencia[3][14]=0;
         matrizAdyacencia[3][15]=0;
         
         matrizAdyacencia[4][0]=0;
         matrizAdyacencia[4][1]=0;
         matrizAdyacencia[4][2]=0;
         matrizAdyacencia[4][3]=0;
         matrizAdyacencia[4][4]=0;
         matrizAdyacencia[4][5]=0;
         matrizAdyacencia[4][6]=0;
         matrizAdyacencia[4][7]=0;
         matrizAdyacencia[4][8]=0;
         matrizAdyacencia[4][9]=0;
         matrizAdyacencia[4][10]=0;
         matrizAdyacencia[4][11]=0;
         matrizAdyacencia[4][12]=0;
         matrizAdyacencia[4][13]=0;
         matrizAdyacencia[4][14]=0;
         matrizAdyacencia[4][15]=0;
         
       
         System.out.println("ancho:"+ancho);
         System.out.println("alto:"+alto);*/
        // matrizAdyacencia
        int etiqueta = 1;
        int etiquetaaux = 1;

        //Ya binarizada la imagen se analiza si el pixel es 1 se analiza los 4 vecinos y se asigna el valor de 
        //etiqueta en caso contrario solo se incrementa el valor sin ser analizado
        for (int i = 1; i < ancho + 1; i++) {
            for (int j = 1; j < alto + 1; j++) {
                if (matrizBinaria[i][j] == 255) {
                    matrizAdyacencia[i][j] = etiqueta;

                    //Se verifica el vecino inferior
                    if (matrizBinaria[i - 1][j] == 255) {
                        matrizAdyacencia[i - 1][j] = etiqueta;
                    }
                    //Se verifica el vecino superior

                    if (matrizBinaria[i + 1][j] == 255) {
                        matrizAdyacencia[i + 1][j] = etiqueta;
                    }
                    //Se verifica el vecino izquierdo
                    if (matrizBinaria[i][j - 1] == 255) {
                        matrizAdyacencia[i][j - 1] = etiqueta;
                    }
                    //Se verifica el vecino derecho

                    if (matrizBinaria[i][j + 1] == 255) {
                        matrizAdyacencia[i][j + 1] = etiqueta;
                    }
                } //en caso contrario cuando el pixel tiene otro valor diferente de 1 solo se incrementa la etiqueta
                else {
                    etiqueta++;
                }


            }
            etiqueta++;
        }
        /*for(int a=1; a<ancho+1; a++){
         for(int b=1; b<alto+1; b++){
         System.out.print(matrizAdyacencia[a][b]+"+");
         }
         System.out.println();
         }*/



        //ya llena la matrizAdyacencia de etiquetas se procede a verificar la adyacencia entre las etiquetas
        int etiq, etiqArriba, etiqAbajo, etiqDerecha, etiqIzquierda;
        for (int i = 1; i < ancho + 1; i++) {
            for (int j = 1; j < alto + 1; j++) {
                etiq = matrizAdyacencia[i][j];
                etiqArriba = matrizAdyacencia[i - 1][j];
                etiqAbajo = matrizAdyacencia[i + 1][j];
                etiqDerecha = matrizAdyacencia[i][j + 1];
                etiqIzquierda = matrizAdyacencia[i][j - 1];
                if (etiq != 0) {

                    //Aqui se analiza la etiqueta del pixel a analizar con la etiqueta del pixel de arriba
                    if (etiqArriba != 0 && etiq != etiqArriba) {
                        for (int a = 1; a < ancho + 1; a++) {
                            for (int b = 1; b < alto + 1; b++) {
                                if (matrizAdyacencia[a][b] == etiqArriba) {
                                    matrizAdyacencia[a][b] = etiq;
                                }
                            }
                        }
                    }
                    //Aqui se analiza la etiqueta del pixel a analizar con la etiqueta del pixel de abajo
                    if (etiqAbajo != 0 && etiq != etiqAbajo) {
                        for (int a = 1; a < ancho + 1; a++) {
                            for (int b = 1; b < alto + 1; b++) {
                                if (matrizAdyacencia[a][b] == etiqAbajo) {
                                    matrizAdyacencia[a][b] = etiq;
                                }
                            }
                        }
                    }
                    //Aqui se analiza la etiqueta del pixel a analizar con la etiqueta del pixel de la derecha

                    if (etiqDerecha != 0 && etiq != etiqDerecha) {
                        for (int a = 1; a < ancho + 1; a++) {
                            for (int b = 1; b < alto + 1; b++) {
                                if (matrizAdyacencia[a][b] == etiqDerecha) {
                                    matrizAdyacencia[a][b] = etiq;
                                }
                            }
                        }
                    }
                    //Aqui se analiza la etiqueta del pixel a analizar con la etiqueta del pixel de la izquierda

                    if (etiqIzquierda != 0 && etiq != etiqIzquierda) {
                        for (int a = 1; a < ancho + 1; a++) {
                            for (int b = 1; b < alto + 1; b++) {
                                if (matrizAdyacencia[a][b] == etiqIzquierda) {
                                    matrizAdyacencia[a][b] = etiq;
                                }
                            }
                        }
                    }
                }
            }
        }


        int color = 230;

        //En estos ciclos se analiza el valor de las etiquetas para asignarle una valor de color a esa region de 
        //adyacencia y que cuando sea mostrada se muestre con niveles de intensidad de gris diferentes.

        for (int i = 1; i < ancho + 1; i++) {
            for (int j = 1; j < alto + 1; j++) {
                etiq = matrizAdyacencia[i][j];
                if (etiq != 0) {
                    for (int x = 1; x < ancho + 1; x++) {
                        for (int y = 1; y < alto + 1; y++) {
                            //System.out.println("matriz"+matrizAdyacencia[a][b]);
                            //System.out.println("etiq"+etiq);
                            if (matrizAdyacencia[x][y] == etiq) {
                                matrizResultado[x][y] = color;
                                //System.out.println("mar"+matrizResultado[x][y]);
                            }
                        }
                    }

                    //Esto se hace por si al decrementar la variable color da un valor negativo
                    color = color - 30;
                    if (color <= 0) {
                        color = 200;
                    }
                }

            }
        }
        /*for(int a=1; a<ancho+1; a++){
         for(int b=1; b<alto+1; b++){
         System.out.print(matrizResultado[a][b]+"+");
         }
         System.out.println();
         }*/


        //Aqui se muestra matrizResultado donde ya estan la adyacencia
        //ImageProcessor ady = null;
        ady = new ByteProcessor(ancho, alto);
        for (int j = 1; j <= ancho; j++) {
            for (int i = 1; i <= alto; i++) {
                /*if(matrizAdyacencia[j][i]!=0){
                 ady.putPixel(j,i, 150);
                 }
                 else*/
                ady.putPixel(j, i, matrizResultado[j][i]);
                imgGuardada.putPixel(j, i, matrizResultado[j][i]);

            }
        }
       // new ImagePlus("Adyacencia", ady).show();
        new ImagePlus("Adyacencia", ady);
        //MainFrm.jLabel2.setIcon(new ImageIcon(x.tareaTriangulo.getBufferedImage()));
    }

    //Metodo que binariza la imagen dependiendo del valor del pixel los valores menores a 128
    //se les asigna el 0 y a los otros valores 1
    public void binaria() {
        matrizBinaria = new int[ancho + 2][alto + 2];
        int aux = 1;
        for (int a = 0; a < ancho; a++) {
            int aux2 = 1;
            for (int b = 0; b < alto; b++) {
                if (matriz[a][b] >= 128) {
                    matrizBinaria[aux][aux2] = 255;
                } else {
                    matrizBinaria[aux][aux2] = 0;
                }
                aux2++;
            }
            aux++;
        }
        ImageProcessor binaria = null;
        binaria = new ByteProcessor(ancho + 2, alto + 2);
        for (int j = 1; j < ancho + 1; j++) {
            for (int i = 1; i < alto + 1; i++) {
                binaria.putPixel(j, i, matrizBinaria[j][i]);
                imgGuardada.putPixel(j, i, matrizBinaria[j][i]);
            }
        }
        new ImagePlus("Imagen binaria", binaria).show();



    }

    public void sacarRotacion(int grados) {
        /*Creación de una nueva imagen llamada "rotar" en blanco con las mismas 
         * medidas de la imagen que se desea procesar, ya que para mostrar la 
         * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
         * las 2 en diferentes ventanas*/

        /*Declaración de la matriz que se utilizara para guardar los valores 
         * alterados por las operaciones de la matriz original*/

        rotacion = new ByteProcessor(ancho, alto);
        int matrizRotacion[][] = new int[ancho][alto];
        int x = 0, y = 0;
        int pi = 0;

        /*Determinamos el centro de la rotación haciendo que gire en su mismo eje
         * esto mediante la división del ancho y alto entre dos respectivamente*/
        int centrox = (int) (ancho / 2);
        int centroy = (int) (alto / 2);//
        int xn = 0, yn = 0;//índices de la matrizRotacion
        /*El usuario da el numero de grados, estos se convierten a radianes para
         su posterior uso en los senos y cosenos*/
        double ang = (grados * Math.PI) / 180;
        /*En estos dos ciclos se sacan las posiciones de la matriz de rotacion en las 
         cuales se va ir guardando los valores de la imagen*/
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                /*Posiciones rotadas de la matrizRotacion (índices de la matrizRotacion)*/
                yn = (int) ((centrox + (j - centrox) * Math.cos(ang) - (i - centroy) * Math.sin(ang)));
                xn = (int) ((centroy + (j - centrox) * Math.sin(ang) + (i - centroy) * Math.cos(ang)));



                //Si las nuevas posxniciones son mayor a 225 o menor a 0 se descartan
                if (xn < 0 || yn < 0 || xn > ancho - 1 || yn > alto - 1) {
                } else {
                    //se asignan los pixeles en la matrizRotacion
                    matrizRotacion[xn][yn] = matriz[i][j];
                    //rotacion.putPixel(xn,yn,matrizRotacion[xn][yn]);
                }
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                rotacion.putPixel(i, j, matrizRotacion[i][j]);
                imgGuardada.putPixel(i, j, matrizRotacion[i][j]);
            }
        }





        /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       // new ImagePlus("Rotacion de la imagen", rotacion).show();
        new ImagePlus("Rotacion de la imagen", rotacion);
                
    }

    public void umbral(int media, int ban) {
        int matrizUmbral[][] = new int[ancho][alto];
        /*Creación de una nueva imagen llamada "umbral" en blanco con las mismas 
         * medidas de la imagen que se desea procesar, ya que para mostrar la 
         * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
         * las 2 en diferentes ventanas*/
        umbral = new ByteProcessor(ancho, alto);
        /*La umbralización convierte una imagen en escala de grises en una imagen en blanco y negro. 
         -	Para ello se determina un umbral que define el valor del píxel, si el píxel analizado es menor 
         *       o igual que el umbral toma el valor de 0, de lo contrario toma el valor de 1.
         *       Para la aplicación si el valor de umbralización no es dado por el usario, 
         *       entonces se calcula la media, la cual es comparada para obtener una imagen binaria*/
        //Mediante este if estamos determinando que el usuario no dio ningún valor de umbralización
        if (media == -1) {
            /*En estos dos ciclos se hace el calculo de la media, esto se hace utilizando
             una variable de tipo entero llamada media, que tiene que ir acumulando el valor
             que tiene mas el valor del pixel de la matriz*/
            for (int a = 0; a < ancho; a++) {
                for (int b = 0; b < alto; b++) {
                    //Suma de lo que tiene media mas el valor del pixel de la matriz
                    media = media + matriz[a][b];
                }
            }
            /*Calculo de la media que es todos los valores de la matriz sumados entre 
             la multiplicación del ancho por alto de la matriz */
            media = media / (ancho * alto);
        }
        /*En estos dos ciclos se va umbralizar la imagen*/
        for (int a = 0; a < ancho; a++) {
            for (int b = 0; b < alto; b++) {
                /*Mediante la condición si el valor del pixel de la matriz es mayor o igual
                 al valor de la variable media entonces matrizUmbral es igual a 255*/
                if (matriz[a][b] >= media) {
                    matrizUmbral[a][b] = 255;
                } /*En otro caso, es decir; que el valor del pixel sea menor al valor de media,
                 entonces matrizUmbral es igual a 0.*/ else {
                    matrizUmbral[a][b] = 0;
                }
                /*La matrizUmbral se asigna a la imagen "umbral" mediante el método putPixel, 
                 * cual tiene como parámetros la posición del pixel y el valor del pixel de 
                 * la matrizUmbral*/
                umbral.putPixel(a, b, matrizUmbral[a][b]);
                imgGuardada.putPixel(a, b, matrizUmbral[a][b]);
            }
        }
        if (ban == 1) {
            /*Con este método se manda a guardar la imagen*/
            //guardarImagen(umbral);
            /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
           // new ImagePlus("Umbral de la imagen", umbral).show();
            new ImagePlus("Umbral de la imagen", umbral);
        }
    }

     
    
    //En este método solo se resalta un rango que da el usuario haciendo que esa región se vea mas clara
    //y el resto de la imgen quede igual
    
    public void intensityLevel(int min, int max){
        int  matrizIntensity[][]=new int [ancho][alto];
        intensity =  new ByteProcessor(ancho,alto);
        for (int x=0;x<ancho;x++){
            for (int y=0;y <alto;y++){
                if(matriz[x][y]>=min&&matriz[x][y]<=max){
                    matrizIntensity[x][y]=255;
                }
                else{
                   matrizIntensity[x][y]=matriz[x][y]; 
                }
                intensity.putPixel(x,y,matrizIntensity[x][y]);
                imgGuardada.putPixel(x,y,matrizIntensity[x][y]);
            }
        }
       // new ImagePlus("Intensity Level",intensity).show();
        new ImagePlus("Intensity Level",intensity);
    }
         
    public void bitPlaneSlicing(){
        /*Declaración de los 8 planos para una imagen de 8 bits de profundidad.
         * Donde las 8 capas, corresponden al valor del bit en la n-ésima posición, 
         * donde n=8.*/
        int [][] plano0=new int[ancho][alto];
        int [][] plano1=new int[ancho][alto];
        int [][] plano2=new int[ancho][alto];
        int [][] plano3=new int[ancho][alto];
        int [][] plano4=new int[ancho][alto];
        int [][] plano5=new int[ancho][alto];
        int [][] plano6=new int[ancho][alto];
        int [][] plano7=new int[ancho][alto];
        /*Creación de 8 imágenes llamada "filtroN" (donde N va de 0 a 7)
         * en blanco con las mismas medidas de la imagen que se desea procesar, 
         * ya que para mostrar la nueva imagen se desea mantener lo anterior sin 
         * modificaciones y mostrar en diferentes ventanas*/
        ImageProcessor filtro0 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro1 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro2 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro3 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro4 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro5 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro6 =  new ByteProcessor(ancho,alto);
        ImageProcessor filtro7 =  new ByteProcessor(ancho,alto);
        /*Variables que guardan los valores de de la posición del pixel que se esta analizando
         en bit que va de 0 a 7*/ 
        int po=0;
        int pr0=0;
        int pr1=0;
        int pr2=0;
        int pr3=0;
        int pr4=0;
        int pr5=0;
        int pr6=0;
        int pr7=0;
        /*En estos 2 ciclos lo que se hace es recorrer la matriz. 
         * En donde po se guarda el valor del pixel de la matriz de posicion [x][y],
         * donde x E [0, ancho de la matriz] 
         *       y E [0, alto de la matriz] 
         * Se envía el pixel y la posción de cada uno de los pixeles a la función
         * conversion binario", con la finalidad de convertir el pixel a binario de 8 posiciones,
         * Esto se hace, de las variables anteriores se guarda el valor del bit de cada posición 
         * que va de 0 a 7, luego de convertirlo a binario se regresa el valor y se guarda 
         * dependiendo de su posición en el plano que le correponde*/
        for (int x=0;x<ancho;x++){
            for (int y=0;y <alto;y++){
                po=matriz[x][y];
                pr0=conversionBinario(po,0);
                            plano0[x][y]=pr0;
                            pr1=conversionBinario(po,1);
                            plano1[x][y]=pr1;
                            pr2=conversionBinario(po,2);
                            plano2[x][y]=pr2;
                            pr3=conversionBinario(po,3);
                            plano3[x][y]=pr3;
                            pr4=conversionBinario(po,4);
                            plano4[x][y]=pr4;
                            pr5=conversionBinario(po,5);
                            plano5[x][y]=pr5;
                            pr6=conversionBinario(po,6);
                            plano6[x][y]=pr6;
                            pr7=conversionBinario(po,7);
                            plano7[x][y]=pr7;
                            }
        
       }
       /*En estos for anidados se guarda los palnos que se crearon con el método bit plane slicing
        en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0;x<ancho;x++){
            for (int y=0;y <alto;y++){
                filtro0.putPixel(x,y,plano0[x][y]);
                imgBit1.putPixel(x,y,plano0[x][y]);
                filtro1.putPixel(x,y,plano1[x][y]);
                imgBit2.putPixel(x,y,plano1[x][y]);
                filtro2.putPixel(x,y,plano2[x][y]);
                imgBit3.putPixel(x,y,plano2[x][y]);
                filtro3.putPixel(x,y,plano3[x][y]);
                imgBit4.putPixel(x,y,plano3[x][y]);
                filtro4.putPixel(x,y,plano4[x][y]);
                imgBit5.putPixel(x,y,plano4[x][y]);
                filtro5.putPixel(x,y,plano5[x][y]);
                imgBit6.putPixel(x,y,plano5[x][y]);
                filtro6.putPixel(x,y,plano6[x][y]);
                imgBit7.putPixel(x,y,plano6[x][y]);
                filtro7.putPixel(x,y,plano7[x][y]);
                imgBit8.putPixel(x,y,plano7[x][y]);
            } 
       }
       banBit=1;
       /*Con estos métodos se manda a imprimir la imagen modificada por las operaciones*/
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro0);
       
      // MainFrm.mBinarias.jLabel1.setIcon(new ImageIcon(filtro0.getBufferedImage()));
       
      // MainFrm.mBinarias.jLabel1.setIcon(new ImageIcon(filtro0.getBufferedImage()));
       
       
      // new ImagePlus("Plano 0",filtro0).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro1);
      // new ImagePlus("Plano 1",filtro1).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro2);
       //new ImagePlus("Plano 2",filtro2).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro3);
       //new ImagePlus("Plano 3",filtro3).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro4);
       //new ImagePlus("Plano 4",filtro4).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro5);
      // new ImagePlus("Plano 5",filtro5).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro6);
       //new ImagePlus("Plano 6",filtro6).show();
       /*Con este método se manda a guardar la imagen*/
       //guardarImagen(filtro7);
       //new ImagePlus("Plano 7",filtro6).show();
    }
    public int conversionBinario(int pix, int pos){
        /* - El método recibe el pixel y la posición, 
         * - En la variable binario se guardan el valor del pixel convertido en binario
         * - Se declara un vector de 8 posiciones incializado en cero
         * - Se guarda en el vector la variable binario posición a posición, 
         *   del más significativo al menos significativo
         * - En la posición val se guarda valor del vector binario dependiendo del valos de pos, 
         *   el cual indica que
         *   bit se esta tratando
         * - En la variable r se le asigna lo que tiene val multiplicada por la 
         *   potencia elevada a la poscion indicada.
         * - Se compara si r es mayor que cero se le asigna 255 
         * - El valor es retornado al método BitPlaneSlicing*/
        int val=0;
        int r=0;
        String binario=Integer.toBinaryString(pix);
        int [] bin=new int[8];
        for(int i=0;i<8;i++) { bin[i]=0;}
        for(int i=0; i<binario.length();i++) {
          bin[i]=Integer.parseInt(Character.valueOf(binario.charAt(i)).toString());
        }
        val=bin[pos];
        r=((int) Math.pow(2, pos))*val;
        if(r>0){
            r=255;
        }
        return r;
    }

    public void histograma(int min, int max){
        int matrizEcualizada[][]=new int [ancho][alto];
        int[] histograma=new int[256];
        double[] vectorNormalizado=new double[256];
        /*Creación de una nueva imagen llamada "histo" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        histo =  new ByteProcessor(ancho,alto);
        int c=0;
        /*Un histograma es una función discreta que cuenta el número de apariciones 
         * de un determinado valor de intensidad.
         * En estos dos ciclos  cuenta cuantos valores de 0 a 255 tiene la imagen
         * para crear el histograma, estos se guarandan en el vector "histograma" 
         * donde cada posición del vector tiene el numero que se repite ese valor 
         * en la imagen. */ 
         for (int a=0; a<ancho; a++) {
                for (int b=0; b<alto; b++){
                    /* En la siguiente línea se obtiene el valor del pixel de la
                     * matriz de la imagen a ecualizar y se asigna a una variable
                     * x de tipo entero.
                     * x sirve como índice para el vector histograma*/
                    int x=matriz[a][b];
                    /* Para la aplicación el usuario definirá cual es el rango del
                     * histograma que se desea ecualizar. Mediante las variables min y max,
                     * en min se guarda el inicio del histograma y en max el fin del histograma,
                     * por lo que en la siguiente condición se determina:*/
                    /*Si el valor del pixel guardado en x, esta en el rango de min y max, es decir;
                    si x es mayor o igual a min y x es menor o igual a max, entonces el vector
                    histograma en su posición x es igual a lo que tiene en su posición x más uno*/
                    if(x>=min&&x<=max)
                        histograma[x]=histograma[x]+1;
                    // En otro caso el vector histograma en su posición x es igual a cero.
                    else
                        histograma[x]=0;
                }
            }
         for (int  x=0; x<256; x++) {
              System.out.println(histograma[x]);
         }
        /*En el siguiente ciclo se normaliza el histograma.
         * Un histograma normalizado es aquél en el que el valor del histograma 
         * ha sido escalado para ajustarse a un rango entre 0 y 1, esto se hace
         * mediante la siguiente formula:
         *  vectorNormalizado[i]=x/mxn;
         * donde:
         *  vectorNormalizado: es donde se guardaran los valores normalizados de 0 a 255
         *  i: es el indice del vector de 0 a 255
         *  x: es el valor del histograma que va de 0 a 255
         * mxn ancho x alto de la imagen a ecualizar*/
        //double c=0;
        double mxn=(ancho*alto);
        double x;
        for(int i=0;i<255;i++){
            /*Se obtiene el número de apariciones de un determinado valor de intensidad del vector
            histograma y este se guarda en la variable x de tipo double*/
            x=histograma[i];
            /*Se normaliza el histograma dividiendo x entre la multiplicación del ancho*alto de la
             matriz, esto se gurada en un vector llamado vectorNormalizado */
            vectorNormalizado[i]=x/mxn;
            //c=c+vectorNormalizado[i];
        }
        /* En el siguiente ciclo se obtiene la ecualización. L a ecualización es 
         * una forma de manipulación de histogramas que reduce automáticamente 
         * el contraste en las áreas muy claras o muy obscuras de una imagen. 
         * También expande los niveles de gris a lo largo de todo el intervalo 
         * del histograma, esto se hace mediante la siguiente formula:
         *      suma=suma+(255*vectorNormalizado[i]);
         *  ecualizador[i]=(int)suma;
         * donde:
         *  255*vectorNormalizado[i]: es el valor ecualizado
         *  suma: es una variable de tipo acumulador ya que conforme va aumentando el índice
         *  va sumando lo de la posición anterior con el valor ecualizado de la posicion i. 
         *  El resultado se le asigna al vector ecualizador en su posición i
         *  vectorNormalizado: es donde se guardaran los valores normalizados de 0 a 255
         *  i: es el índice del vector de 0 a 255
         *  ecualizador: vector donde se guardan los valores ecualizados*/
        int [] ecualizador=new int [256];
        double suma=0;
        for(int i=0;i<255;i++){
            /*En la siguiente línea se va acumulando en la variable suma de tipo double
             * la multiplicación del valor normalizado por 255*/
            suma=suma+(255*vectorNormalizado[i]);
            /*La variable suma se guarda en un vector llamado ecualizador convirtiendo suma
             * a double*/
            ecualizador[i]=(int)suma;
            System.out.println(ecualizador[i]);
        }
       /*En los dos ciclos se ecualiza la imagen, donde el valor del pixel de la matriz
        * sirve como índice del vector ecualizado*/         
       for (int a=0; a<ancho; a++) {
           for (int b=0;b<alto; b++){
                /*Como se puede definir el rango del histograma se necesita saber que valores
                 * serán ecualizados, esto se hace mediante las siguientes condiciones:
                 Si ecualizador en su posición pixel de la matriz es distinto de cero, entonces
                 matrizEcualizada es igual al valor del vector ecualizado*/
                if(ecualizador[matriz[a][b]]!=0){
                    matrizEcualizada[a][b]=ecualizador[matriz[a][b]];
                }
                /*En otro caso matrizEcualizada es igual al valor del pixel de la 
                matriz de la imagen original*/
                else{
                    matrizEcualizada[a][b]=matriz[a][b];
                }
                /*La matrizEcualizada se asigna a la imagen "histo" mediante el método putPixel, 
                * cual tiene como parámetros la posición del pixel y el valor del pixel de 
                * la matrizEcualizada*/
                histo.putPixel(a,b,matrizEcualizada[a][b]);
                imgGuardada.putPixel(a,b,matrizEcualizada[a][b]);
            }
       }
       /*Con este método se manda a guardar la imagen*/
       // guardarImagen(histo);
       /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       //new ImagePlus("Ecualización de la imagen",histo).show(); 
         new ImagePlus("Ecualización de la imagen",histo);
       
       System.out.println("_____________________");
       for (int a=0; a<ancho; a++) {
                for (int b=0; b<alto; b++){
                    int x1=matrizEcualizada[a][b];
                    if(x1>=min&&x1<=max)
                        histograma[x1]=histograma[x1]+1;
                    else
                        histograma[x1]=0;
                }
            }
         for (int x1=0; x1<256; x1++) {
              System.out.println(histograma[x1]);
         }
    }
    public void expansion(int min, int max){
        //declaracion de variables
        int matrizExpandida[][]=new int [ancho][alto];
        if(min==0&&max==0){
            /*Con estos dos ciclos se obtiene el máximo y el mínimo de la matriz
         * mediante la ayuda de dos max de tipo entero la cual guarda el máximo
         * y min de tipo entero que guarda el mínimo.
         * Estas variables inicialmente guardan el primer valor de la matriz*/
            for (int a=0; a<ancho; a++) {
                for (int b=0;b<alto; b++){
               /*Para obtener el máximo de la matriz se compara que el valor del 
                * pixel de la matriz sea mayor a max, si eso sucede entonces max
                * guarda el valor del pixel con el que fue comparado*/
                    if(matriz[a][b]>max){
                        max=matriz[a][b];
                    }
                    else{
                /*Si el valor del pixel no es mayor a max, entonces se compara 
                 * que el valor del pixel de la matriz sea menor a min , si eso 
                 * sucede entonces max guarda el valor del pixel con el que fue comparado*/
                        if(matriz[a][b]<min){
                            min=matriz[a][b];
                        }
                    
                    }
                }
            }
        }
        
       System.out.println("max:"+max);
       System.out.println("min:"+min);
       //obtencion del rango medinate la resta de max menos min
       int rango=max-min;
       /*Creación de una nueva imagen llamada "expansión" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        expansion =  new ByteProcessor(ancho,alto);
        /*En este dos ciclo se guardan los pixeles modificados por la operación expansión lineal*/
        for (int a=0; a<ancho; a++) {
                for (int b=0; b<alto; b++){
                    /* En esta línea se realiza la expansión lineal que permite que 
                     * se expanda y cubra la totalidad del rango de valores disponibles.
                     * esto se hace mediante la división de la multiplicación de 255 por la resta de el 
                     * pixel de la matriz menos su valor mínimo entre la resta del máximo menos mínimo (esta
                     * resta se gurada en la variable rango).*/
                    matrizExpandida[a][b]=(255*(matriz[a][b]-min))/rango;
                    /*La matrizExpandida se asigna a la imagen "expansion" mediante el método putPixel, 
                    * cual tiene como parámetros la posición del pixel y el valor del pixel de 
                    * la matrizExpandida*/
                    expansion.putPixel(a,b,matrizExpandida[a][b]);
                    imgGuardada.putPixel(a,b,matrizExpandida[a][b]);
                }
        }
        /*Con este método se manda a guardar la imagen*/
        //guardarImagen(expansion);
       /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
      // new ImagePlus("Expansión lineal de la imagen",expansion).show(); 
        new ImagePlus("Expansión lineal de la imagen",expansion);
    }
    
    public void obtenerImagen(File copia) throws IOException{
        //Se obtiene la imagen 
        imagen= null;
        imagen = ImageIO.read( copia);
        //Se obtiene el ancho y el alto
        ancho = imagen.getWidth ();
        alto = imagen.getHeight ();
        //declaracion de variables es este caso se utilizan para la obtención de los pixeles
        //en formato ARGB donde A significa el brillo que tiene la imagen
        int a=0,rojo=0,verde=0,azul=0;
        int pixel=0;
        //se inicializan las matrices
        //matrizAlpha=new int [ancho][alto];
        matrizRed=new int [ancho][alto];
        matrizGreen=new int [ancho][alto];
        matrizBlue=new int [ancho][alto];
        
        Opener op = new Opener();
        String nombre=String.valueOf(copia);
        //imagenO = op.openImage(nombre);
       // op.open(nombre);
        for (int i=0; i<alto; i++) {
            for (int j= 0; j <ancho; j++){
                //Se obtiene el valor del pixel RGB de tipo entero
                pixel = imagen.getRGB (j, i);
                /* getRGB devuelve un entero representando el color RGB, 
                 * utilizando los bits para indicar la cantidad de cada uno de 
                 * los componentes rojo, verde y azul que entran en su composición. 
                 * Los bits 24 a 31 del entero que devuelve el método son 0xff (alpha), 
                 * los bits 16 a 23 son el valor rojo, los bits 8 a 15 son el valor verde 
                 * y los bits 0 a 7 indican el valor del color azul. Siempre en el rango 0 a 255.
                 * Estos valores se guardan en las variables a es el valor alpha que es el brillo de la imagen
                 * rojo guardo el valor red de la imagen
                 * verde guarda el valor green de la imagen
                 * azul guarda el valor blue de la imagen*/
                a = (pixel >> 24) & 0xff;
                rojo = (pixel >> 16) & 0xff;
                verde = (pixel >> 8) & 0xff;
                azul = (pixel) & 0xff;
 
                //cada uno de los colores se guarda en una matriz
                matrizRed[j][i]=rojo;
                matrizGreen[j][i]=verde;
                matrizBlue[j][i]=azul;
            }
        }
        
        
    }
    
    
    public void sacarCyan(){
        //Se crea una imagen RGB que guardara los pixeles modificados dependiendo de la bandas de colores        
        cp= null;
        cp = new ColorProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int r = 0;
        final int g = 1;
        final int b = 2;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                //para crear el cyan en la posicion cero de RGB se guarda el cero
                
                
                int z = matrizGreen[u][v]-matrizBlue[u][v];
                int w = z + matrizBlue[u][v];
                
                RGB[r] = 0;
                RGB[g] = w;
                RGB[b] =  w;
                
                
                
                //se guardan los valores en la imgen creada
                cp.putPixel(u, v, RGB);
            }
        }


        //Se desplega la imegen
        ImagePlus cwin = new ImagePlus("Cyan", cp);
        //cwin.show();
    }
    
    public void sacarMagenta(){
         //Se crea una imagen RGB que guardara los pixeles modificados dependiendo de la bandas de colores          
         cp= null;
        cp = new ColorProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int a = 0;
        final int r = 0;
        final int g = 1;
        final int b = 2;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
          //para crear el Magenta en la posicion uno de RGB se guarda el cero
                
                
                              
                
                RGB[r] = 255-matrizRed[u][v];
                RGB[g] = 0;
                RGB[b] = 255-matrizBlue[u][v];
                cp.putPixel(u, v, RGB);
            }
        }
        
        

        ImagePlus cwin = new ImagePlus("Magenta", cp);
        //cwin.show();
    }
    public void sacarAmarillo(){
        //Se crea una imagen RGB que guardara los pixeles modificados dependiendo de la bandas de colores             
               cp= null;
               cp = new ColorProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int a = 0;
        final int r = 0;
        final int g = 1;
        final int b = 2;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                
    //para crear el Amarillo en la posicion dos de RGB se guarda el cero
                RGB[r] = 255-matrizRed[u][v];
                RGB[g] = 255-matrizGreen[u][v];
                RGB[b] = 0;
                cp.putPixel(u, v, RGB);
            }
        }

        ImagePlus cwin = new ImagePlus("Amarillo", cp);
        //cwin.show();
    }
    public void sacarGris(){
             
              cp=null;
              cp = new ColorProcessor(ancho,alto);
        //ImageProcessor cp = new ByteProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int a = 0;
        final int r = 0;
        final int g = 1;
        final int b = 2;
        int idx=0;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                
                
//Se calcula con el promedio de los 3 pixeles

                RGB[r] = ((matrizRed[u][v]+ matrizGreen[u][v]+ matrizBlue[u][v])/3);
                RGB[g] = ((matrizRed[u][v]+ matrizGreen[u][v]+ matrizBlue[u][v])/3);
                RGB[b] = ((matrizRed[u][v]+ matrizGreen[u][v]+ matrizBlue[u][v])/3);
                cp.putPixel(u, v, RGB);
            }
        }


        ImagePlus cwin = new ImagePlus("Escala de grises", cp);
        //cwin.show();
    }
    
    
    public void sacarRojo(){
         cp=null;    
         cp = new ColorProcessor(ancho,alto);
        //ImageProcessor cp = new ByteProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int a = 0;
        final int r = 0;
        final int g = 1;
        final int b = 2;
        int idx=0;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                RGB[r] = 255-matrizRed[u][v];
                RGB[g] = 0;
                RGB[b] = 0;
                
               //Aqui se guarda el total de los pixeles de cada banda 
                aux= matrizRed[u][v] + aux;
                aux1= matrizGreen[u][v] + aux1;
                aux2= matrizBlue[u][v] + aux2;

                                
                cp.putPixel(u, v, RGB);
            }
           
        }

        
            /**System.out.println("Totalr" + aux);
            System.out.println("Totalv" + aux1);
            System.out.println("Totala" + aux2);

*/
        
        //Se suma el valor de las 3 bandas para que dependiendo de eso se tome como 100% y posteriormente sacar el
        //% de aportación de cada banda con la formula de la regla de 3 donde 100 coresponde al 100% total aux,aux1 y aux 2
        //son las variables que contienen el total de cada banda entre la suma de esas 3 variables que es aux3
            aux3=aux+aux1+aux2;
            //System.out.println("Total" + aux3);
            
            PR=((100*aux)/aux3);
            PV=((100*aux1)/aux3);
            PA=((100*aux2)/aux3);
          //  System.out.println("Total rojo " + PR);
           // System.out.println("Total verde " + PV);
            //System.out.println("Total azul " + PA);


       
        ImagePlus cwin = new ImagePlus("Rojo  "+ PR +"% de aportación", cp);
        //cwin.show();
    }
    
    
    public void sacarVerde(){
        cp=null;     
        cp = new ColorProcessor(ancho,alto);
        //ImageProcessor cp = new ByteProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int a = 0;
        final int r = 0;
        final int g = 1;
        final int b = 2;
        int idx=0;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                RGB[r] = 0;
                RGB[g] = 255-matrizRed[u][v];
                RGB[b] = 0;
                
                 //Aqui se guarda el total de los pixeles de cada banda 
                aux= matrizRed[u][v] + aux;
                aux1= matrizGreen[u][v] + aux1;
                aux2= matrizBlue[u][v] + aux2;

              
                cp.putPixel(u, v, RGB);
            }
        }
//Se suma el valor de las 3 bandas para que dependiendo de eso se tome como 100% y posteriormente sacar el
        //% de aportación de cada banda con la formula de la regla de 3 donde 100 coresponde al 100% total aux,aux1 y aux 2
        //son las variables que contienen el total de cada banda entre la suma de esas 3 variables que es aux3
            aux3=aux+aux1+aux2;
          //  System.out.println("Total" + aux3);
            
            PR=((100*aux)/aux3);
            PV=((100*aux1)/aux3);
            PA=((100*aux2)/aux3);
           // System.out.println("Total rojo " + PR);
            //System.out.println("Total verde " + PV);
            //System.out.println("Total azul " + PA);


        ImagePlus cwin = new ImagePlus("Verde  "+ PV +"% de aportación", cp);
        //cwin.show();
    }
    
    public void sacarAzul(){
         cp=null;    
         cp = new ColorProcessor(ancho,alto);
        //ImageProcessor cp = new ByteProcessor(ancho,alto);
        //Arreglo guardara los valores RGB que forman un pixel, en la posción cero el rojo, en la uno el verde
        //y en la 2 el azul
        int[] RGB = new int[3];
        final int a = 0;
        final int r = 0;
        final int g = 1;
        final int b = 2;
        int idx=0;
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                RGB[r] = 0;
                RGB[g] = 0;
                RGB[b] = 255-matrizRed[u][v];
                
                 //Aqui se guarda el total de los pixeles de cada banda 
                aux= matrizRed[u][v] + aux;
                aux1= matrizGreen[u][v] + aux1;
                aux2= matrizBlue[u][v] + aux2;

              
                cp.putPixel(u, v, RGB);
            }
        }
        //Se suma el valor de las 3 bandas para que dependiendo de eso se tome como 100% y posteriormente sacar el
        //% de aportación de cada banda con la formula de la regla de 3 donde 100 coresponde al 100% total aux,aux1 y aux 2
        //son las variables que contienen el total de cada banda entre la suma de esas 3 variables que es aux3
            aux3=aux+aux1+aux2;
           // System.out.println("Total" + aux3);
            
            PR=((100*aux)/aux3);
            PV=((100*aux1)/aux3);
            PA=((100*aux2)/aux3);
           // System.out.println("Total rojo " + PR);
           // System.out.println("Total verde " + PV);
           // System.out.println("Total azul " + PA);



        ImagePlus cwin = new ImagePlus("Azul  "+ PA +"% de aportación", cp);
        //cwin.show();
    }
    
   
    
     
    public void filtroPasoBajo(int op){
       /*Creación */
       int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        filtro= null;
        filtro =  new ByteProcessor(ancho,alto);
        /* En este if se asignan los valores de la mascara de un flitro de caja 
         * o promedio y de un filtro gaussiano.
         * op es una variable con la que se controla la opción que el usuario elige.
         * si la op==1, elige un filtro de caja o promedio, la mascara que se utilizará
         * sera la siguiente:
         * Mascara 1:
         * [ 1,1,1
         *   1,1,1
         *   1,1,1]x(1/9)
         * En otro caso elegiste el filtro gaussiano y la mascara a utilizar es:
         * Mascara 2:
         * [ 1,2,1
         *   2,4,2
         *   1,2,1]x(1/16)*/
        int a,b,c,d,e,f,g,h,i,j=1;
        if(op==1){
            a=1;b=1;c=1;d=1;e=1;f=1;g=1;h=1;i=1;j=9;
        }
        else{
             a=1;b=2;c=1;d=2;e=2;f=1;g=2;h=1;i=4;j=16;
        }
        /*En este metodo se hace una copia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
         * filas y columnas.
         * El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
         * el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                matrizExtendida[x+1][y+1]=matriz[x][y];
            }
        }
        /*En este metodo se hace la operación de convolución, que es la suma de los 8
         * vecinos del pixel a tratar, donde cada uno de los vecinos se multiplica por 
         * el valor correspondiente de la mascara (ya sea el filtro de caja o gaussiano).
         * El ciclo empieza en [1][1] y termina en el ancho y alto de matriz original
         * También se hace la comprobación de que los valores no sobrepasen
         * el rango de 0 a 255*/
        int valorO=0;
        for (int x=1; x<ancho; x++) {
            for (int y=1; y<alto; y++){
                valorO=((a*matrizExtendida[x-1][y-1])+
                        (b*matrizExtendida[x-1][y])+
                        (c*matrizExtendida[x-1][y+1])+
                        (d*matrizExtendida[x][y-1])+
                        (e*matrizExtendida[x][y+1])+
                        (f*matrizExtendida[x+1][y-1])+
                        (g*matrizExtendida[x+1][y])+
                        (h*matrizExtendida[x+1][y+1])+
                        (i*matrizExtendida[x][y]))/j;
                if(valorO>0&&valorO<255){
                    valorO=valorO;
                }
                else{
                    if(valorO<0){
                        valorO=0;
                    }
                    else{
                        if(valorO>255){
                        valorO=255;
                        }
                    }
                }
                matrizFiltro[x][y]=valorO;
            }
        }
       /*En estos for anidados se gurada la matriz a la que se le aplico alguna de las mascaras
        en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,matrizFiltro[x+1][y+1]);
                imgGuardada.putPixel(x,y,matrizFiltro[x+1][y+1]);
            }
        }
       /* Con este metodo se manda a imprimir la imagen modificada por las operaciones.
       * En la cadena cad se guarda el nombre que va tener como titulo la ventana 
       * donde se muestra la imagen, con el fin de identificar que mascara fue aplicada
       * si la de filtro de caja o promedio o Filtro el Gaussiano*/
       String cad="";
       if(op==1)
           cad="Filtro de caja o promedio";
       
       else
           cad="Filtro Gaussiano";
       /*Con este método se manda a guardar la imagen*/
        //guardarImagen(filtro);
      // new ImagePlus(cad,filtro).show();
       new ImagePlus(cad,filtro);
       
         ImagePlus v = new ImagePlus();
         v.setProcessor(filtro);
         HistogramWindow w = new HistogramWindow(v);
         w.setForeground(Color.white);
         w.setSize(500, 400);
         w.setBackground(Color.DARK_GRAY);
        
         w.setLocationRelativeTo(null);
         //w.setFont(null);
         w.setVisible(true);
       
       
    }
    public void filtroMediana(){
        

        
        
        
        int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
       filtro= null;
        filtro =  new ByteProcessor(ancho,alto);
        int mascara[][]=new int [3][3];
        /* En este metodo se hace una opia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
         * filas y columnas.
         * El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
         * el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                matrizExtendida[x+1][y+1]=matriz[x][y];
            }
        }
        /* En este método se obtienen los 8 vecino del pixel a tratar los cuales 
         * son los valores de la mascara. La mascara a utilizar es de 3x3.
         * Los pixeles a tratar empieza en [1][1] y termina en ancho y alto de 
         * la matriz original para colocarlos en la mascara. 
         * Después de que los vecinos del pixel a tratar se guarden en la mascara
         * esta se ordena y se toma el valor de la mediana (La mediana es el 
         * valor que divide en dos partes iguales un conjunto de valores) 
         * de la mascara, y ese es el valor nuevo del pixel a tratar.
         * Para ordenar la mascara en este método se utilizo el método de 
         * ordenación de burbuja, el cual ordena de menor a mayor.
         * Por tanto el nuevo valor del pixel a tratar es el valor de la mascara
         * en su posición [1][1]*/
        for (int x=1; x<ancho; x++) {
            for (int y=1; y<alto; y++){
                mascara[0][0]=matrizExtendida[x-1][y-1];
                mascara[0][1]=matrizExtendida[x-1][y];
                mascara[0][2]=matrizExtendida[x-1][y+1];
                mascara[1][0]=matrizExtendida[x][y-1];
                mascara[1][1]=matrizExtendida[x][y];
                mascara[1][2]=matrizExtendida[x][y+1];
                mascara[2][0]=matrizExtendida[x+1][y-1];
                mascara[2][1]=matrizExtendida[x+1][y];
                mascara[2][2]=matrizExtendida[x+1][y+1];
                for( int i=0; i < 3; i++){//ordena la matriz de abajo hacia arriba
                    for( int j=0;j< 3; j++){
                        for(int a=0; a < 3; a++){
                            for(int b=0; b<3; b++){
                                if(mascara[i][j] < mascara[a][b]){
                                    int t = mascara[i][j];
                                    mascara[i][j] = mascara[a][b];
                                    mascara[a][b] = t;
                                }
                            }
                        }
                    }
                } 
                matrizFiltro[x][y]=mascara[1][1];
                
            }
        }
        /* En estos for anidados se gurada la matriz a la que se le aplico alguna de las mascara
        * en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,matrizFiltro[x+1][y+1]);
                imgGuardada.putPixel(x,y,matrizFiltro[x+1][y+1]);
            }
        }
       /*Con este método se manda a guardar la imagen*/
        //guardarImagen(filtro);
       /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       //new ImagePlus("Filtro mediana",filtro).show();
       new ImagePlus("Filtro mediana",filtro);
              
         ImagePlus a = new ImagePlus();
         a.setProcessor(filtro);
         HistogramWindow b = new HistogramWindow(a);
        // b.setBounds(500, 300, 400, 250);
         b.setForeground(Color.white);
         b.setSize(500, 400);
         b.setBackground(Color.DARK_GRAY);
        
         b.setLocationRelativeTo(null);
         b.setVisible(true);
       
    }
    public void filtroModa(){
       int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
       filtro= null;
        filtro =  new ByteProcessor(ancho,alto);
        int mascara[][]=new int [3][3];
        /*En este metodo se hace una opia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
          filas y columnas.
          El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
          el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        //variable para gusrdar el resultado de la operacion
        int operacion;
        //declaracion de los vecinos 
        //Vecino superior izquierdo 
        int vsi=0;
        //Vecino superior 
        int vs=0;
        //Vecino superior derecho 
        int vsd=0;
        //Vecino derecho 
        int vd=0;
        //Vecino inferior derecho
        int vid=0;
        //Vecino inferior del pixel analizado
        int vi=0;
        //Valor original del vecino 
        int vii=0;
        // Valor vecino izquierdo del pixel analizado
        int viz=0;
        //Pixcel analisado
        int c=0;
        //Variable temporal que guarda la posicin de cada arreglo para pasarceloa nuestra imagen nueva
        int p=0;

   //declaracion de una nueva matriz que contendra el contenido de nuestra imagen nuea
        int mat1[][]=new int[ancho+2][alto+2];
        int mat2[][]=new int[ancho][alto];
        for (int i=1; i<ancho; i++) {
            for (int j=1; j<alto; j++){
            mat1[i][j]=matriz[i-1][j-1];
            }
        }
        //inicialización de la imagen
        mascara[0][0]=1;
        mascara[0][1]=1;
        mascara[0][2]=1;
        mascara[1][0]=1;
        mascara[1][1]=1;
        mascara[1][2]=1;
        mascara[2][0]=1;
        mascara[2][1]=1;
        mascara[2][2]=1;
        for (int i=1; i<ancho; i++) {
            for (int j=1; j<alto; j++){
               //asignacion de los valores de la matriz a las variables de vecinos
                //para realizar la moda
                vsi=mat1[i-1][j-1]*mascara[0][0];
                vs=mat1[i-1][j]*mascara[0][1];
                vsd=mat1[i-1][j+1]*mascara[0][2];
                vd=mat1[i][j+1]*mascara[1][2];
                vid=mat1[i+1][j+1]*mascara[2][0];
                vi=mat1[i+1][j]*mascara[2][1];
                vii=mat1[i+1][j-1]*mascara[2][2];              
                viz=mat1[i][j-1]*mascara[1][0];
                c=mat1[i][j]*mascara[1][1];
       //asignaciónn de los valores en el vector donde se guardaran los valores de 
       //la mascara
       int vect[]=new int[9];
              vect[0]=vsi;
              vect[1]=vs;
              vect[2]=vsd;
              vect[3]=vd;
              vect[4]=vid;
              vect[5]=vi;
              vect[6]=vii;
              vect[7]=viz;
              vect[8]=c;
         //calculamos la moda para posteriormete asignar el valor a la imagen nueva
        int rep = 0;
        int moda = 0;
        //calculo la moda, anteriormente se agregaron los vecinos multiplicados por la mascara
        //a un vector  despues en el vector le asigno el sigiuiente algoritmo
       //que compara cada uno de los elementos del vector para ver cuntas veces se repite y lo 
        //gurdo en la varible veces 
        //una ves recorrido todo el vestor saco el valor que mas repite 
        //y es el valor que le agrego a mi matriz 
       
             for(int b=0; b<vect.length; b++){
                     int veces = 0;
                     for(int d=0; d<vect.length; d++){
                            if(vect[b] == vect[d])
                             rep++;
                       }
                             if(veces> rep){
           
                                 moda = vect[b];
                                 rep = veces;
                               }
                       }
              mat2[i-1][j-1]=rep;
        }
     }
     for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,mat2[x][y]);
                imgGuardada.putPixel(x,y,mat2[x][y]);
                //matrizFiltro[x][y]=matrizExtendida[x+1][y+1];
            }
        }
     /*Con este método se manda a guardar la imagen*/
       // guardarImagen(filtro);
        /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
      // new ImagePlus("Filtro moda",filtro).show();
       
        new ImagePlus("Filtro moda",filtro);
       
       
      //   new ImagePlus("Moda", nueva).show();
         ImagePlus a = new ImagePlus();
         a.setProcessor(filtro);
         HistogramWindow b = new HistogramWindow(a);
         b.setForeground(Color.white);
         b.setSize(500, 400);
         b.setBackground(Color.DARK_GRAY);
        
         b.setLocationRelativeTo(null);
         b.setVisible(true);
       
       
       
    }
    
    public void filtroMaximo(){
       int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        ImageProcessor filtro =  new ByteProcessor(ancho,alto);
        int mascara[][]=new int [3][3];
        /*En este metodo se hace una copia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
          filas y columnas.
          El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
          el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                matrizExtendida[x+1][y+1]=matriz[x][y];
            }
        }
        /* En este método se obtienen los 8 vecino del pixel a tratar los cuales 
         * son los valores de la mascara. La mascara a utilizar es de 3x3.
         * Los pixeles a tratar empieza en [1][1] y termina en ancho y alto de 
         * la matriz original para colocarlos en la mascara. 
         * Después de que los vecinos del pixel a tratar se guarden en la mascara
         * esta se ordena y se toma el valor máximo o más grande de la mascara,
         * y ese es el valor nuevo del pixel a tratar,.
         * Para ordenar la mascara en este método se utilizo el método de 
         * ordenación de burbuja, el cual ordena de menor a mayor.
         * Por tanto el nuevo valor del pixel a tratar es el valor de la mascara
         * en su posición [2][2]*/
        for (int x=1; x<ancho; x++) {
            for (int y=1; y<alto; y++){
                mascara[0][0]=matrizExtendida[x-1][y-1];
                mascara[0][1]=matrizExtendida[x-1][y];
                mascara[0][2]=matrizExtendida[x-1][y+1];
                mascara[1][0]=matrizExtendida[x][y-1];
                mascara[1][1]=matrizExtendida[x][y];
                mascara[1][2]=matrizExtendida[x][y+1];
                mascara[2][0]=matrizExtendida[x+1][y-1];
                mascara[2][1]=matrizExtendida[x+1][y];
                mascara[2][2]=matrizExtendida[x+1][y+1];
                for( int i=0; i < 3; i++){//ordena la matriz de abajo hacia arriba
                    for( int j=0;j< 3; j++){
                        for(int a=0; a < 3; a++){
                            for(int b=0; b<3; b++){
                                if(mascara[i][j] < mascara[a][b]){
                                    int t = mascara[i][j];
                                    mascara[i][j] = mascara[a][b];
                                    mascara[a][b] = t;
                                }
                            }
                        }
                    }
                } 
                matrizFiltro[x][y]=mascara[2][2];
                
            }
        }
       /* En estos for anidados se gurada la matriz a la que se le aplico alguna de las mascara
        * en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,matrizFiltro[x+1][y+1]);
                imgGuardada.putPixel(x,y,matrizFiltro[x+1][y+1]);
            }
        }
       /*Con este método se manda a guardar la imagen*/
       // guardarImagen(filtro);
       /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       new ImagePlus("Filtro máximo",filtro).show();
          ImagePlus a = new ImagePlus();
         a.setProcessor(filtro);
         HistogramWindow b = new HistogramWindow(a);
         b.setVisible(true);
       
    }
    public void filtroMinimo(){
        /*Declaración de matrices*/
       int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        ImageProcessor filtro =  new ByteProcessor(ancho,alto);
        int mascara[][]=new int [3][3];
        /*En este metodo se hace una opia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
          filas y columnas.
          El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
          el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        for (int x=0; x<ancho; x++) {
      /*En este metodo se hace una opia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
          filas y columnas.
          El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
          el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/      for (int y=0; y<alto; y++){
                matrizExtendida[x+1][y+1]=matriz[x][y];
            }
        }
        /* En este método se obtienen los 8 vecino del pixel a tratar los cuales 
         * son los valores de la mascara. La mascara a utilizar es de 3x3.
         * Los pixeles a tratar empieza en [1][1] y termina en ancho y alto de 
         * la matriz original para colocarlos en la mascara. 
         * Después de que los vecinos del pixel a tratar se guarden en la mascara
         * esta se ordena y se toma el valor minímo o más pequeño de la mascara,
         * y ese es el valor nuevo del pixel a tratar,.
         * Para ordenar la mascara en este método se utilizo el método de 
         * ordenación de burbuja, el cual ordena de menor a mayor.
         * Por tanto el nuevo valor del pixel a tratar es el valor de la mascara
         * en su posición [0][0]*/
        for (int x=1; x<ancho; x++) {
            for (int y=1; y<alto; y++){
                mascara[0][0]=matrizExtendida[x-1][y-1];
                mascara[0][1]=matrizExtendida[x-1][y];
                mascara[0][2]=matrizExtendida[x-1][y+1];
                mascara[1][0]=matrizExtendida[x][y-1];
                mascara[1][1]=matrizExtendida[x][y];
                mascara[1][2]=matrizExtendida[x][y+1];
                mascara[2][0]=matrizExtendida[x+1][y-1];
                mascara[2][1]=matrizExtendida[x+1][y];
                mascara[2][2]=matrizExtendida[x+1][y+1];
                for( int i=0; i < 3; i++){//ordena la matriz de abajo hacia arriba
                    for( int j=0;j< 3; j++){
                        for(int a=0; a < 3; a++){
                            for(int b=0; b<3; b++){
                                if(mascara[i][j] < mascara[a][b]){
                                    int t = mascara[i][j];
                                    mascara[i][j] = mascara[a][b];
                                    mascara[a][b] = t;
                                }
                            }
                        }
                    }
                } 
                matrizFiltro[x][y]=mascara[0][0];
            }
        }
        /*En estos for anidados se gurada la matriz a la que se le aplico alguna de las mascaras
        en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,matrizFiltro[x+1][y+1]);
                imgGuardada.putPixel(x,y,matrizFiltro[x+1][y+1]);
            }
        }
       /*Con este método se manda a guardar la imagen*/
       // guardarImagen(filtro);
       /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       new ImagePlus("Filtro minímo",filtro).show();
           ImagePlus a = new ImagePlus();
         a.setProcessor(filtro);
         HistogramWindow b = new HistogramWindow(a);
         b.setVisible(true);
       
    }
    public void filtroRangoN(int n){
       int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        ImageProcessor filtro =  new ByteProcessor(ancho,alto);
        int mascara[]=new int[9];
        /*En este metodo se hace una opia de la matriz original con el fin de 
         * tratar los bordes agrgando columnas/filas con un valor de 0 para esas nuevas
         * filas y columnas.
         * El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
         * el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                matrizExtendida[x+1][y+1]=matriz[x][y];
            }
        }
        /* En este método se obtienen los 8 vecino del pixel a tratar los cuales 
         * son los valores de la mascara. La mascara a utilizar es de 3x3.
         * Los pixeles a tratar empieza en [1][1] y termina en ancho y alto de 
         * la matriz original para colocarlos en la mascara. 
         * Después de que los vecinos del pixel a tratar se guarden en la mascara
         * esta se ordena y se toma el valor n-esimo (en este caso 
         * el usuario lo propone y puede ser un valor de 1 a 9, 
         * que son los numero de pixeles que tiene la mascara) 
         * y ese es el valor nuevo del pixel a tratar.
         * Para ordenar la mascara en este método se utilizo el metodo 
         * short de java*/
        int t=0;
        int c=0;
        for (int x=1; x<=ancho; x++) {
            for (int y=1; y<=alto; y++){
                mascara[0]=matrizExtendida[x-1][y-1];
                mascara[1]=matrizExtendida[x-1][y];
                mascara[2]=matrizExtendida[x-1][y+1];
                mascara[3]=matrizExtendida[x][y-1];
                mascara[4]=matrizExtendida[x][y];
                mascara[5]=matrizExtendida[x][y+1];
                mascara[6]=matrizExtendida[x+1][y-1];
                mascara[7]=matrizExtendida[x+1][y];
                mascara[8]=matrizExtendida[x+1][y+1];
                Arrays.sort(mascara);
                matrizFiltro[x][y]=mascara[n-1];
            }
        }
       /*En estos for anidados se gurada la matriz a la que se le aplico alguna de las mascaras
        en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,matrizFiltro[x+1][y+1]);
                imgGuardada.putPixel(x,y,matrizFiltro[x+1][y+1]);
            }
        }
       /*Con este método se manda a guardar la imagen*/
       // guardarImagen(filtro);
       /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       new ImagePlus("Filtro rango n",filtro).show();
           ImagePlus a = new ImagePlus();
         a.setProcessor(filtro);
         HistogramWindow b = new HistogramWindow(a);
         b.setVisible(true);
       
    }
    
    public void filtroLaplaciano(int op){
       int matrizExtendida[][]=new int [ancho+2][alto+2];
       int matrizFiltro[][]=new int [ancho+2][alto+2];
       /*Creación de una nueva imagen llamada "filtro" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener lo anterior sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        filtro= null;
        filtro =  new ByteProcessor(ancho,alto);
        int a,b,c,d,e,f,g,h,i,j=1;
        /* En este if se asignan los valores de la mascara de un flitro laplaciano,
         * Nota: Un filtro laplaciano es la segunda derivada, 
         * que a partir de la expresión del operador Laplaciano podemos aproximar
         * con la siguientes mascaras:
         * Mascara 1:
         * [ 0,-1,0
         *  -1,5,-1
         *  0,-1,0]
         * Mascara 2:
         * [ -1,-1,-1
         *   -1, 9,-1
         *   -1,-1,-1]
         * En donde:
         * Si op==1 la mascara es de un filtro de 4 vecinos
           En otro caso si op==2 la mascara es un filtro 8 vecinos*/
        if(op==1){
            a=0;b=-1;c=0;d=-1;e=-1;f=0;g=-1;h=0;i=5;
        }
        else{
             a=-1;b=-1;c=-1;d=-1;e=-1;f=-1;g=-1;h=-1;i=9;
        }
        /*En este metodo se hace una copia de la matriz original con el fin de 
         * tratar los bordes agregando columnas/filas con un valor de 0 para esas nuevas
         * filas y columnas.
         * El metodo es empezar a llenar la matriz extendia en su posicion [1][1] hasta
         * el ancho (numero de filas) y alto(numero de columnas) de la matriz original*/
        for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                matrizExtendida[x+1][y+1]=matriz[x][y];
            }
        }
        double valorO=0;
        /* En este metodo se hace la operacion de laplaciano, que es la suma de los 8
         * vecinos del pixel a tratar. Cada uno de los vecinos se multiplica por 
         * el valor correspondiente de la mascara.
         * El ciclo empieza en [1][1] y termina en el ancho y alto de matriz original.
         * Tambien se tiene que hacer la comprobación de que los valores no sobrepasen
         * el rango de 0 a 255*/
        for (int x=1; x<ancho; x++) {
            for (int y=1; y<alto; y++){
                valorO=((a*matrizExtendida[x-1][y-1])+
                        (b*matrizExtendida[x-1][y])+
                        (c*matrizExtendida[x-1][y+1])+
                        (d*matrizExtendida[x][y-1])+
                        (e*matrizExtendida[x][y+1])+
                        (f*matrizExtendida[x+1][y-1])+
                        (g*matrizExtendida[x+1][y])+
                        (h*matrizExtendida[x+1][y+1])+
                        (i*matrizExtendida[x][y]));
                if(valorO>=0 && valorO<=255){
                    matrizFiltro[x][y]=(int)valorO;
                }
                else{
                    if(valorO<0){
                      matrizFiltro[x][y]=0;  
                    }
                    else{
                        if(valorO>255){
                            matrizFiltro[x][y]=255;
                        }
                    }
                }
            }
        }
       /*En estos for anidados se gurada la matriz a la que se le aplico alguna de las mascaras
        en una variable de tipo imagen para poder visualizarla en un ventana*/
       for (int x=0; x<ancho; x++) {
            for (int y=0; y<alto; y++){
                filtro.putPixel(x,y,matrizFiltro[x+1][y+1]);
                imgGuardada.putPixel(x,y,matrizFiltro[x+1][y+1]);
            }
        }
       /* Con este metodo se manda a imprimir la imagen modificada por las operaciones.
          * En la cadena cad se guarda el nombre que va tener como titulo la ventana 
          * donde se muestra la iamgen, con el fin de identificar que mascara fue aplicada*/
       String cad="";
       if(op==1)
           cad="Filtro 4 vecinos Laplaciano";
       else
           cad="Filtro 8 vecionos Laplaciano";
       /*Con este método se manda a guardar la imagen*/
      //  guardarImagen(filtro);
       new ImagePlus(cad,filtro);
       
           ImagePlus x = new ImagePlus();
         x.setProcessor(filtro);
         HistogramWindow p = new HistogramWindow(x);
         p.setForeground(Color.white);
         p.setSize(500, 400);
         p.setBackground(Color.DARK_GRAY);
        
         p.setLocationRelativeTo(null);
         p.setVisible(true);
       
    }
    
    //Método expand
    //expand (if (sigma>0) Q0=1;else Q0=P0]])
    //en este caso sigma es la variable suma

                    
    public void expand(){
         int [][] matrizExpansion=new int [ancho+2][alto+2];
         int suma=0;
                 
         for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
                
                //Se suman los 8 pixeles vecinos del pixel a analizar
                suma=matrizBinaria[x-1][y-1]+matrizBinaria[x-1][y]+matrizBinaria[x-1][y+1]+
                     matrizBinaria[x][y-1]+matrizBinaria[x][y+1]+
                       matrizBinaria[x+1][y-1]+matrizBinaria[x+1][y]+matrizBinaria[x+1][y+1];

                //Si la suma de estos 8 pixeles es mayor a cero se le asigna 255
                //en otro caso se queda con su valor original
                if(suma>0){
                    matrizExpansion[x][y]=255;
                }
                else{
                   matrizExpansion[x][y]=matrizBinaria[x][y];
                  //  matrizExpansion[x][y]=0;
                }
                
            }
         }
               //Mandamos a pintar la matrizExpansion
       //ImageProcessor ady =null;
        ady =  new ByteProcessor(ancho,alto);
        for(int j=1;j<=ancho;j++){
            for(int i=1;i<=alto;i++){
                /*if(matrizAdyacencia[j][i]!=0){
                    ady.putPixel(j,i, 150);
                }
                else
                     ady.putPixel(j,i, matrizExpansion[j][i]);*/
               ady.putPixel(j,i, matrizExpansion[j][i]);
               imgGuardada.putPixel(j,i, matrizExpansion[j][i]);
            }
        }
       // new ImagePlus("Expand",ady).show();
         new ImagePlus("Expand",ady);
    }
    
    //Método Shrink
    ////shrink if (sigma<8) Q0=0;  else Q0=P0]]   
    //en este caso sigma es la variable suma
    
      public void shrink (){
         int [][] matrizExpansion=new int [ancho+2][alto+2];
         int suma=0;
                 
         for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
   //Se suman los 8 pixeles vecinos del pixel a analizar
                suma=matrizBinaria[x-1][y-1]+matrizBinaria[x-1][y]+matrizBinaria[x-1][y+1]+
                     matrizBinaria[x][y-1]+matrizBinaria[x][y+1]+
                       matrizBinaria[x+1][y-1]+matrizBinaria[x+1][y]+matrizBinaria[x+1][y+1];
                //System.out.println(suma);
                 //Si la suma de estos 8 pixeles es menor a 2040 que es la suma de los 8 piexeles asumiendo 
                //que el valor maximo que puede tener es 255 asignamos un 0 en otro caso se queda con su valor original
               
                if(suma<2040){
                    matrizExpansion[x][y]=0;
                }
                else{
                   matrizExpansion[x][y]=matrizBinaria[x][y];
                  //  matrizExpansion[x][y]=0;
                }
                
            }
         }
        
         
         //Mandamos a pintar la matriz
        ady =null;
        ady =  new ByteProcessor(ancho,alto);
        for(int j=1;j<=ancho;j++){
            for(int i=1;i<=alto;i++){
               ady.putPixel(j,i, matrizExpansion[j][i]);
               imgGuardada.putPixel(j,i, matrizExpansion[j][i]);
            }
        }
        //new ImagePlus("Shrink",ady).show();
          new ImagePlus("Shrink",ady);
    }
    
    
      
      //Para este método existen diferentes elementos estructurantes
      //Máscaras de desplazamiento
      //Máscaras asimétricas
      //Da un total de 13 máscaras
    public void dilatacion(int [][] mascara){
        int [][] matrizDilatacion=new int [ancho+2][alto+2];
        int a=1, b=1;
        for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
  //Se recorre la matrizBinaria ya que solo se analizan los que tienen como valor de pixel 255
                if(matrizBinaria[x][y]==255){
      //La máscara es de matriz de  3x3    a b c
     //                                    d e f
       //                                  g h i dada por el usuario pero solo se analizan los casos cuando
       //la posición de la matriz es 1
                    
                    
                    //Se analiza que a de la máscara sea igual a 1 para poner un 255 en esa posición 
                    if(mascara[a-1][b-1]==1){
                        matrizDilatacion[x-1][y-1]=255;
                    }
                //Se analiza que b de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a-1][b]==1){
                        matrizDilatacion[x-1][y]=255;
                    }
                 //Se analiza que c de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a-1][b+1]==1){
                        matrizDilatacion[x-1][y+1]=255;
                    }
                //Se analiza que d de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a][b-1]==1){
                        matrizDilatacion[x][y-1]=255;
                    }
                    
            //Se analiza que e de la máscara sea igual a 1 para poner un 255 en esa posición 
                    //solo en este caso si el pixel tiene 0 se queda con 0 no cambia

                    if(mascara[a][b]==1){
                        matrizDilatacion[x][y]=255;
                    }else{
                        matrizDilatacion[x][y]=0;
                    }
        //Se analiza que f de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a][b+1]==1){
                        matrizDilatacion[x][y+1]=255;
                    }
                   
         //Se analiza que g de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b-1]==1){
                        matrizDilatacion[x+1][y-1]=255;
                    }
         //Se analiza que h de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b]==1){
                        matrizDilatacion[x+1][y]=255;
                    }
         //Se analiza que i de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b+1]==1){
                        matrizDilatacion[x+1][y+1]=255;
                    }
                }
            }
        } 
        
        
        //Mandamos pintar la matrizDilatacion
        ady =null;
        ady =  new ByteProcessor(ancho,alto);
        for(int j=1;j<=ancho;j++){
            for(int i=1;i<=alto;i++){
        //for(int j=0;j<5;j++){
          //  for(int i=0;i<5;i++){
               ady.putPixel(j,i, matrizDilatacion[j][i]);
               imgGuardada.putPixel(j,i, matrizDilatacion[j][i]);
                //System.out.print(matrizDilatacion[j][i]);
            }
            //System.out.println();
        }
       //new ImagePlus("Dilatación",ady).show();
        new ImagePlus("Dilatación",ady);
    }
    
    
   //Método erosion
    //La erosión puede verse como el conjunto de elementos para los que la traslación
    //del elemento estructurante está contenida en la imagen
    
    //Es como aplicar una operación lógica AND entre el elemento estructurante y la imagen.
    
    
      //Para este método existen diferentes elementos estructurantes
      //Máscaras asimétricas
      //Da un total de 6 máscaras
   
  public void erosion(int [][] mascara){
        int [][] matrizDilatacion=new int [ancho+2][alto+2];
        int a=1, b=1;
        for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
  //Se recorre la matrizBinaria ya que solo se analizan los que tienen como valor de pixel 255

                if(matrizBinaria[x][y]==255){
                    
                    
 //La máscara es de matriz de  3x3    a b c
//                                    d e f
  //                                  g h i dada por el usuario pero solo se analizan los casos cuando
       //la posición de la matriz es 1 en todas las máscaras el valor de e es igual a 1
     
                    if(mascara[a-1][b-1]==1){
                        matrizDilatacion[x-1][y-1]=0;
                    }
                    
          //Se analiza que en la matriz máscara en la posición b exista un uno y al mismo tiempo en matriz binaria
             // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
                    
                    if(mascara[a-1][b]==1 && matrizBinaria[x-1][y]==255){
                        matrizDilatacion[x-1][y]=matrizBinaria[x-1][y];
                        matrizDilatacion[x][y]=255;
                                                
                    } else {
                                        
                        matrizDilatacion[x-1][y]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
                    
                    if(mascara[a-1][b+1]==1){
                        matrizDilatacion[x-1][y+1]=0;
                    }
      //Se analiza que en la matriz máscara en la posición d exista un uno y al mismo tiempo en matriz binaria
     // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a][b-1]==1 && matrizBinaria[x][y-1]==255 ){
                        
                        matrizDilatacion[x][y-1]=matrizBinaria[x][y-1];
                        matrizDilatacion[x][y]=255;
                    }else {
                                        
                        matrizDilatacion[x][y-1]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
     //Se analiza que en la matriz máscara en la posición f exista un uno y al mismo tiempo en matriz binaria
        // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a][b+1]==1 && matrizBinaria[x+1][y-1]==255){
                        matrizDilatacion[x][y+1]= matrizBinaria[x+1][y-1];
                        matrizDilatacion[x][y]=255;

                    }
                   else {
                                        
                        matrizDilatacion[x][y+1]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
                    if(mascara[a+1][b-1]==1 ){
                        matrizDilatacion[x+1][y-1]=255;
                    }
                    
                
     //Se analiza que en la matriz máscara en la posición h exista un uno y al mismo tiempo en matriz binaria
     // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a+1][b]==1 && matrizBinaria[x+1][y]==255){
                        matrizDilatacion[x+1][y]= matrizBinaria[x+1][y];
                        matrizDilatacion[x][y]=255;

                    } else {
                                        
                        matrizDilatacion[x+1][y]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
                  
                    if(mascara[a+1][b+1]==1){
                        matrizDilatacion[x+1][y+1]=0;
                    }
                }
            }
        } 
        
        //mandamos pintar la matriz
        ady =null;
        ady =  new ByteProcessor(ancho,alto);
        for(int j=1;j<=ancho;j++){
            for(int i=1;i<=alto;i++){
               ady.putPixel(j,i, matrizDilatacion[j][i]);
               imgGuardada.putPixel(j,i, matrizDilatacion[j][i]);
            }
        }
       // new ImagePlus("Erosion",ady).show();
        new ImagePlus("Erosion",ady);
    }
  
  
  
  //Método clausura
    //Suaviza secciones de contornos.
   //Fusiona/une pequeños puentes.
   //Elimina/llena orificios pequeños.
  //Primero se aplica el método de dilatación y despues el método de erosión
  

   public void clausura(int [][] mascara){
        int [][] matrizDilatacion=new int [ancho+2][alto+2];
        int [][] matrizClausura=new int [ancho+2][alto+2];
        int a=1, b=1;
        for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
  //Se recorre la matrizBinaria ya que solo se analizan los que tienen como valor de pixel 255
                if(matrizBinaria[x][y]==255){
      //La máscara es de matriz de  3x3    a b c
     //                                    d e f
       //                                  g h i dada por el usuario pero solo se analizan los casos cuando
       //la posición de la matriz es 1
                    
                    
                    //Se analiza que a de la máscara sea igual a 1 para poner un 255 en esa posición 
                    if(mascara[a-1][b-1]==1){
                        matrizDilatacion[x-1][y-1]=255;
                    }
                //Se analiza que b de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a-1][b]==1){
                        matrizDilatacion[x-1][y]=255;
                    }
                 //Se analiza que c de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a-1][b+1]==1){
                        matrizDilatacion[x-1][y+1]=255;
                    }
                //Se analiza que d de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a][b-1]==1){
                        matrizDilatacion[x][y-1]=255;
                    }
                    
            //Se analiza que e de la máscara sea igual a 1 para poner un 255 en esa posición 
                    //solo en este caso si el pixel tiene 0 se queda con 0 no cambia

                    if(mascara[a][b]==1){
                        matrizDilatacion[x][y]=255;
                    }else{
                        matrizDilatacion[x][y]=0;
                    }
        //Se analiza que f de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a][b+1]==1){
                        matrizDilatacion[x][y+1]=255;
                    }
                   
         //Se analiza que g de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b-1]==1){
                        matrizDilatacion[x+1][y-1]=255;
                    }
         //Se analiza que h de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b]==1){
                        matrizDilatacion[x+1][y]=255;
                    }
         //Se analiza que i de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b+1]==1){
                        matrizDilatacion[x+1][y+1]=255;
                    }
                }
            }
        } 
       
        
        for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
  //Se recorre la matrizDilatacion ya que solo se analizan los que tienen como valor de pixel 255

                if(matrizDilatacion[x][y]==255){
                    
                    
 //La máscara es de matriz de  3x3    a b c
//                                    d e f
  //                                  g h i dada por el usuario pero solo se analizan los casos cuando
       //la posición de la matriz es 1 en todas las máscaras el valor de e es igual a 1
     
                    if(mascara[a-1][b-1]==1){
                        matrizClausura[x-1][y-1]=0;
                    }
                    
          //Se analiza que en la matriz máscara en la posición b exista un uno y al mismo tiempo en matriz binaria
             // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
                    
                    if(mascara[a-1][b]==1 && matrizBinaria[x-1][y]==255){
                        matrizClausura[x-1][y]=matrizBinaria[x-1][y];
                        matrizClausura[x][y]=255;
                                                
                    } else {
                                        
                        matrizClausura[x-1][y]=0;
                        matrizClausura[x][y]=0;
                    
                    }
                    
                    if(mascara[a-1][b+1]==1){
                        matrizClausura[x-1][y+1]=0;
                    }
      //Se analiza que en la matriz máscara en la posición d exista un uno y al mismo tiempo en matriz binaria
     // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a][b-1]==1 && matrizBinaria[x][y-1]==255 ){
                        
                        matrizClausura[x][y-1]=matrizBinaria[x][y-1];
                        matrizClausura[x][y]=255;
                    }else {
                                        
                        matrizClausura[x][y-1]=0;
                        matrizClausura[x][y]=0;
                    
                    }
     //Se analiza que en la matriz máscara en la posición f exista un uno y al mismo tiempo en matriz binaria
        // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a][b+1]==1 && matrizBinaria[x+1][y-1]==255){
                        matrizClausura[x][y+1]=matrizBinaria[x+1][y-1];
                        matrizClausura[x][y]=255;

                    }
                   else {
                                        
                        matrizClausura[x][y+1]=0;
                        matrizClausura[x][y]=0;
                    
                    }
                    if(mascara[a+1][b-1]==1 ){
                        matrizClausura[x+1][y-1]=255;
                    }
                    
                
     //Se analiza que en la matriz máscara en la posición h exista un uno y al mismo tiempo en matriz binaria
     // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a+1][b]==1 && matrizBinaria[x+1][y]==255){
                        matrizClausura[x+1][y]=matrizBinaria[x+1][y];
                        matrizClausura[x][y]=255;

                    } else {
                                        
                        matrizClausura[x+1][y]=0;
                        matrizClausura[x][y]=0;
                    
                    }
                  
                    if(mascara[a+1][b+1]==1){
                        matrizClausura[x+1][y+1]=0;
                    }
                }
            }
        } 
        
        

         ady =null;
        ady =  new ByteProcessor(ancho,alto);
        for(int j=1;j<=ancho;j++){
            for(int i=1;i<=alto;i++){
        //for(int j=0;j<5;j++){
          //  for(int i=0;i<5;i++){
               ady.putPixel(j,i, matrizClausura[j][i]);
               imgGuardada.putPixel(j,i, matrizClausura[j][i]);
                //System.out.print(matrizDilatacion[j][i]);
            }
            //System.out.println();
        }
       //new ImagePlus("Clausura",ady).show();
        new ImagePlus("Clausura",ady);
    }

   //Mètodo Apertura
  // Suaviza el contorno de un objeto.
//Rompe pequeños puentes.
   //Primero se aplica el método de erosión y despues el método de dilatación
  
      public void apertura(int [][] mascara){
          
          
           int [][] matrizApertura=new int [ancho+2][alto+2];
           int [][] matrizDilatacion=new int [ancho+2][alto+2];
       int a=1, b=1;
        for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
  //Se recorre la matrizBinaria ya que solo se analizan los que tienen como valor de pixel 255

                if(matrizBinaria[x][y]==255){
                    
                    
 //La máscara es de matriz de  3x3    a b c
//                                    d e f
  //                                  g h i dada por el usuario pero solo se analizan los casos cuando
       //la posición de la matriz es 1 en todas las máscaras el valor de e es igual a 1
     
                    if(mascara[a-1][b-1]==1){
                        matrizDilatacion[x-1][y-1]=0;
                    }
                    
          //Se analiza que en la matriz máscara en la posición b exista un uno y al mismo tiempo en matriz binaria
             // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
                    
                    if(mascara[a-1][b]==1 && matrizBinaria[x-1][y]==255){
                        matrizDilatacion[x-1][y]=matrizBinaria[x-1][y];
                        matrizDilatacion[x][y]=255;
                                                
                    } else {
                                        
                        matrizDilatacion[x-1][y]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
                    
                    if(mascara[a-1][b+1]==1){
                        matrizDilatacion[x-1][y+1]=0;
                    }
      //Se analiza que en la matriz máscara en la posición d exista un uno y al mismo tiempo en matriz binaria
     // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a][b-1]==1 && matrizBinaria[x][y-1]==255 ){
                        
                        matrizDilatacion[x][y-1]=matrizBinaria[x][y-1];
                        matrizDilatacion[x][y]=255;
                    }else {
                                        
                        matrizDilatacion[x][y-1]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
     //Se analiza que en la matriz máscara en la posición f exista un uno y al mismo tiempo en matriz binaria
        // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a][b+1]==1 && matrizBinaria[x+1][y-1]==255){
                        matrizDilatacion[x][y+1]= matrizBinaria[x+1][y-1];
                        matrizDilatacion[x][y]=255;

                    }
                   else {
                                        
                        matrizDilatacion[x][y+1]=0;
                        matrizDilatacion[x][y]=0;
                    
                    }
                    if(mascara[a+1][b-1]==1 ){
                        matrizDilatacion[x+1][y-1]=255;
                    }
                    
                
     //Se analiza que en la matriz máscara en la posición h exista un uno y al mismo tiempo en matriz binaria
     // en la misma posicion exista un 255 si en almenos en uno de ellos exista un valor diferente se asigna un 0
          
                    if(mascara[a+1][b]==1 && matrizBinaria[x+1][y]==255){
                        matrizDilatacion[x+1][y]=255;
                        matrizDilatacion[x][y]=255;

                    } else {
                                        
                        matrizDilatacion[x+1][y]= matrizBinaria[x+1][y];
                        matrizDilatacion[x][y]=0;
                    
                    }
                  
                    if(mascara[a+1][b+1]==1){
                        matrizDilatacion[x+1][y+1]=0;
                    }
                }
            }
        } 
          
        
        
        for(int x=1;x<ancho+1;x++){
            for(int y=1;y<alto+1;y++){
  //Se recorre la matrizDilatacion ya que solo se analizan los que tienen como valor de pixel 255
                if(matrizDilatacion[x][y]==255){
      //La máscara es de matriz de  3x3    a b c
     //                                    d e f
       //                                  g h i dada por el usuario pero solo se analizan los casos cuando
       //la posición de la matriz es 1
                    
                    
                    //Se analiza que a de la máscara sea igual a 1 para poner un 255 en esa posición 
                    if(mascara[a-1][b-1]==1){
                        matrizApertura[x-1][y-1]=255;
                    }
                //Se analiza que b de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a-1][b]==1){
                        matrizApertura[x-1][y]=255;
                    }
                 //Se analiza que c de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a-1][b+1]==1){
                        matrizApertura[x-1][y+1]=255;
                    }
                //Se analiza que d de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a][b-1]==1){
                        matrizApertura[x][y-1]=255;
                    }
                    
            //Se analiza que e de la máscara sea igual a 1 para poner un 255 en esa posición 
                    //solo en este caso si el pixel tiene 0 se queda con 0 no cambia

                    if(mascara[a][b]==1){
                        matrizApertura[x][y]=255;
                    }else{
                        matrizApertura[x][y]=0;
                    }
        //Se analiza que f de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a][b+1]==1){
                        matrizApertura[x][y+1]=255;
                    }
                   
         //Se analiza que g de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b-1]==1){
                        matrizApertura[x+1][y-1]=255;
                    }
         //Se analiza que h de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b]==1){
                        matrizApertura[x+1][y]=255;
                    }
         //Se analiza que i de la máscara sea igual a 1 para poner un 255 en esa posición 

                    if(mascara[a+1][b+1]==1){
                        matrizApertura[x+1][y+1]=255;
                    }
                }
            }
        } 
        
         //Mandamos imprimir la matrizApertura

         ady =null;
        ady =  new ByteProcessor(ancho,alto);
        for(int j=1;j<=ancho;j++){
            for(int i=1;i<=alto;i++){
        //for(int j=0;j<5;j++){
          //  for(int i=0;i<5;i++){
               ady.putPixel(j,i, matrizApertura[j][i]);
               imgGuardada.putPixel(j,i, matrizApertura[j][i]);
                //System.out.print(matrizDilatacion[j][i]);
            }
            //System.out.println();
        }
      // new ImagePlus("Apertura",ady).show();
    
      new  ImagePlus("Apertura",ady);
        
        
      }


public void sacarOr(){
        // leerMatrizOperaciones();
          File file, copia;
        
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo PGM(.pgm)", "pgm");
        JFileChooser selector=new JFileChooser();
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Abrir archivo...");
        File ruta = new File("proyecto");
        selector.setCurrentDirectory(ruta);
        int resultado=selector.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
          file=selector.getSelectedFile();
           copia=new File(file.getAbsolutePath());
           String nombre=String.valueOf(copia);
        //Creación del objeto op, se utiliza para 
	Opener op = new Opener();
        //- imagenO:  se guarda la imagen utilizando la ruta guardada en la variable nombre
        System.out.println(nombre);
        imagenOper = op.openImage(nombre);
        /* Se obtien las medidas de la matriz
         - ancho: se guarda el numero de columnas de la imagen
         - alto: se guarda el numero de filas de la imagen*/
        anchoOper=imagenOper.getWidth();
        altoOper=imagenOper.getHeight();
        //Creación del objeto matriz, se utiliza para guardar los pixeles de la imagen
        matrizOperaciones=new int[anchoOper][altoOper];
      //  matrizSegmentacion=new int [ancho][alto];
        //Variable pixel para la obtención de los pixeles de la imagen
        int pixel;
        //Creación de una imagen  apartir de la obtenida en el objeto imagenO
        ImageProcessor ip=imagenOper.getProcessor();
        /* Los siguientes dos for se obtiene los pixeles de la imagen en tipo int, 
         * estos se guardan en la variable pixel con getPixel y se asignan
         * a la matriz para realizar las operaciones correspondientes 
         * (las vistas en la materia de Tratamiento de Imagenes)
         * donde:
         * a; es un índice que controla el ancho de la imagen este va de [0,ancho]
         * b; es un inidce que controla el alto de la imagen este va de [0,alto]
         * La intersección de los índices nos da el pixel*/
        for (int a=0; a<anchoOper; a++) {
            for (int b=0; b<altoOper; b++){
                pixel = ip.getPixel(a, b);
                matrizOperaciones[a][b] = pixel;
            }
        }
        /* Con el método open se abre la imagen a la que se le va hacer las 
         * operaciones correpondientes solo mandando la ruta con la variable nombre*/
        op.open(nombre);
        }
        //en este método se obtiene la ruta de la segunda imagen
        /*si ancho de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al ancho de la segunda iamgen*/
        System.out.println(anchoOper);
        if(ancho>anchoOper){
         ancho=anchoOper;
       }
        /*si alto de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al alto de la segunda iamgen*/
        if(alto>altoOper){
          alto=altoOper;
       }
        System.out.println(anchoOper);
        System.out.println(altoOper);
        System.out.println(ancho);
        System.out.println(ancho);
         /*Creación de una nueva imagen llamada "or" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        or= null;
        or =  new ByteProcessor(ancho,alto);
        /*Declaración de la matriz que se utilizara para gurdar los valores 
         * alterados por las operaciones de la matriz original*/
        int matrizOr[][]=new int [ancho][alto];
       /*En los siguientes dos ciclos se va recorriendo la matriz para poder 
       * asignar el valor de cada posicion a la matriz de copia, para poder sacar
       * la matriz original.
       *    Esto  se hace mediante la instrucción:
       *        x=matriz[a][b]|matrizOperaciones[a][b];
       * donde: 
       *    a E[0,ancho de la matriz]
       *    b E[0,alto de la matriz]
       *    x = se le asigna el valor del pixel de la matrices con el operando or
       * Nota:
       *    para poder realizar or, se tuvo que poner las siguientes condiciones,
       *    para que los niveles de gris fuara del rango de 0 a 255:
       *    si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizSuma
       *    en otro caso se le asigna a la matrizSuma x
       * La matriz copia se asigna a la imagen "suma" mediante el metodo putPixel, 
       * el cual tiene como parametros laposicion del pixel y el valor del pixel*/
        int x=0;
        double d=0;
        for (int a=0; a<ancho; a++) {
            for (int b=0; b<alto; b++){
                       
                x=matriz[a][b] | matrizOperaciones[a][b];
                
                       
                        
               // si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizSuma
             if(x>255){
                    x=x/2;//promedio de los pixeles que es la suma de los pixeles entre dos
                    matrizOr[a][b] =x;//asignación del promedio a la matrizSuma
                }
                //en otro caso se le asigna a la matrizSuma x
                else{
                    matrizOr[a][b] =x;//asignacion del pixel sumado a la matrizSuma
                }
                or.putPixel(a,b, matrizOr[a][b]);
                imgGuardada.putPixel(a,b, matrizOr[a][b]);
             }   
        }
        
   
        //new ImagePlus("OR",or).show();
         new ImagePlus("OR",or);
    }


public void sacarAnd(){
        // leerMatrizOperaciones();
          File file, copia;
        
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo PGM(.pgm)", "pgm");
        JFileChooser selector=new JFileChooser();
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Abrir archivo...");
        File ruta = new File("proyecto");
        selector.setCurrentDirectory(ruta);
        int resultado=selector.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
          file=selector.getSelectedFile();
           copia=new File(file.getAbsolutePath());
           String nombre=String.valueOf(copia);
        //Creación del objeto op, se utiliza para 
	Opener op = new Opener();
        //- imagenO:  se guarda la imagen utilizando la ruta guardada en la variable nombre
        System.out.println(nombre);
        imagenOper = op.openImage(nombre);
        /* Se obtien las medidas de la matriz
         - ancho: se guarda el numero de columnas de la imagen
         - alto: se guarda el numero de filas de la imagen*/
        anchoOper=imagenOper.getWidth();
        altoOper=imagenOper.getHeight();
        //Creación del objeto matriz, se utiliza para guardar los pixeles de la imagen
        matrizOperaciones=new int[anchoOper][altoOper];
      //  matrizSegmentacion=new int [ancho][alto];
        //Variable pixel para la obtención de los pixeles de la imagen
        int pixel;
        //Creación de una imagen  apartir de la obtenida en el objeto imagenO
        ImageProcessor ip=imagenOper.getProcessor();
        /* Los siguientes dos for se obtiene los pixeles de la imagen en tipo int, 
         * estos se guardan en la variable pixel con getPixel y se asignan
         * a la matriz para realizar las operaciones correspondientes 
         * (las vistas en la materia de Tratamiento de Imagenes)
         * donde:
         * a; es un índice que controla el ancho de la imagen este va de [0,ancho]
         * b; es un inidce que controla el alto de la imagen este va de [0,alto]
         * La intersección de los índices nos da el pixel*/
        for (int a=0; a<anchoOper; a++) {
            for (int b=0; b<altoOper; b++){
                pixel = ip.getPixel(a, b);
                matrizOperaciones[a][b] = pixel;
            }
        }
        /* Con el método open se abre la imagen a la que se le va hacer las 
         * operaciones correpondientes solo mandando la ruta con la variable nombre*/
        op.open(nombre);
        }
        //en este método se obtiene la ruta de la segunda imagen
        /*si ancho de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al ancho de la segunda iamgen*/
        System.out.println(anchoOper);
        if(ancho>anchoOper){
         ancho=anchoOper;
       }
        /*si alto de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al alto de la segunda iamgen*/
        if(alto>altoOper){
          alto=altoOper;
       }
        System.out.println(anchoOper);
        System.out.println(altoOper);
        System.out.println(ancho);
        System.out.println(ancho);
         /*Creación de una nueva imagen llamada "or" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
         or= null;
         or =  new ByteProcessor(ancho,alto);
        /*Declaración de la matriz que se utilizara para gurdar los valores 
         * alterados por las operaciones de la matriz original*/
        int matrizAnd[][]=new int [ancho][alto];
       /*En los siguientes dos ciclos se va recorriendo la matriz para poder 
       * asignar el valor de cada posicion a la matriz de copia, para poder sacar
       * la matriz original.
       *    Esto  se hace mediante la instrucción:
       *        x=matriz[a][b]&matrizOperaciones[a][b];
       * donde: 
       *    a E[0,ancho de la matriz]
       *    b E[0,alto de la matriz]
       *    x = se le asigna el valor del pixel de la matrices con el operando And
       * Nota:
       *    para poder realizar and, se tuvo que poner las siguientes condiciones,
       *    para que los niveles de gris fuara del rango de 0 a 255:
       *    si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizAnd
       *    en otro caso se le asigna a la matrizAnd x
       */
        int x=0;
        double d=0;
        for (int a=0; a<ancho; a++) {
            for (int b=0; b<alto; b++){
                       
                x=matriz[a][b] & matrizOperaciones[a][b];
                
                       
                        
               // si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizSuma
             if(x>255){
                    x=x/2;//promedio de los pixeles que es la suma de los pixeles entre dos
                    matrizAnd[a][b] =x;//asignación del promedio a la matrizSuma
                }
                //en otro caso se le asigna a la matrizSuma x
                else{
                    matrizAnd[a][b] =x;//asignacion del pixel sumado a la matrizSuma
                }
                or.putPixel(a,b, matrizAnd[a][b]);
                imgGuardada.putPixel(a,b, matrizAnd[a][b]);
             }   
        }
        
   
        //new ImagePlus("AND",or).show();
        new ImagePlus("AND",or);
    }

public void sacarXor(){
        // leerMatrizOperaciones();
          File file, copia;
        
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo PGM(.pgm)", "pgm");
        JFileChooser selector=new JFileChooser();
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Abrir archivo...");
        File ruta = new File("proyecto");
        selector.setCurrentDirectory(ruta);
        int resultado=selector.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
          file=selector.getSelectedFile();
           copia=new File(file.getAbsolutePath());
           String nombre=String.valueOf(copia);
        //Creación del objeto op, se utiliza para 
	Opener op = new Opener();
        //- imagenO:  se guarda la imagen utilizando la ruta guardada en la variable nombre
        System.out.println(nombre);
        imagenOper = op.openImage(nombre);
        /* Se obtien las medidas de la matriz
         - ancho: se guarda el numero de columnas de la imagen
         - alto: se guarda el numero de filas de la imagen*/
        anchoOper=imagenOper.getWidth();
        altoOper=imagenOper.getHeight();
        //Creación del objeto matriz, se utiliza para guardar los pixeles de la imagen
        matrizOperaciones=new int[anchoOper][altoOper];
      //  matrizSegmentacion=new int [ancho][alto];
        //Variable pixel para la obtención de los pixeles de la imagen
        int pixel;
        //Creación de una imagen  apartir de la obtenida en el objeto imagenO
        ImageProcessor ip=imagenOper.getProcessor();
        /* Los siguientes dos for se obtiene los pixeles de la imagen en tipo int, 
         * estos se guardan en la variable pixel con getPixel y se asignan
         * a la matriz para realizar las operaciones correspondientes 
         * (las vistas en la materia de Tratamiento de Imagenes)
         * donde:
         * a; es un índice que controla el ancho de la imagen este va de [0,ancho]
         * b; es un inidce que controla el alto de la imagen este va de [0,alto]
         * La intersección de los índices nos da el pixel*/
        for (int a=0; a<anchoOper; a++) {
            for (int b=0; b<altoOper; b++){
                pixel = ip.getPixel(a, b);
                matrizOperaciones[a][b] = pixel;
            }
        }
        /* Con el método open se abre la imagen a la que se le va hacer las 
         * operaciones correpondientes solo mandando la ruta con la variable nombre*/
        op.open(nombre);
        }
        //en este método se obtiene la ruta de la segunda imagen
        /*si ancho de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al ancho de la segunda iamgen*/
        System.out.println(anchoOper);
        if(ancho>anchoOper){
         ancho=anchoOper;
       }
        /*si alto de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al alto de la segunda iamgen*/
        if(alto>altoOper){
          alto=altoOper;
       }
        System.out.println(anchoOper);
        System.out.println(altoOper);
        System.out.println(ancho);
        System.out.println(ancho);
         /*Creación de una nueva imagen llamada "or" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        ImageProcessor xor =  new ByteProcessor(ancho,alto);
        /*Declaración de la matriz que se utilizara para gurdar los valores 
         * alterados por las operaciones de la matriz original*/
        int matrizXor[][]=new int [ancho][alto];
       /*En los siguientes dos ciclos se va recorriendo la matriz para poder 
       * asignar el valor de cada posicion a la matriz de copia, para poder sacar
       * la matriz original.
       *    Esto  se hace mediante la instrucción:
       *        x=matriz[a][b]&matrizOperaciones[a][b];
       * donde: 
       *    a E[0,ancho de la matriz]
       *    b E[0,alto de la matriz]
       *    x = se le asigna el valor del pixel de la matrices con el operando And
       * Nota:
       *    para poder realizar and, se tuvo que poner las siguientes condiciones,
       *    para que los niveles de gris fuara del rango de 0 a 255:
       *    si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizAnd
       *    en otro caso se le asigna a la matrizAnd x
       */
        int x=0;
        double d=0;
        for (int a=0; a<ancho; a++) {
            for (int b=0; b<alto; b++){
                       
                x=matriz[a][b]^matrizOperaciones[a][b];
                
                       
                        
               // si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizSuma
             if(x>255){
                    x=x/2;//promedio de los pixeles que es la suma de los pixeles entre dos
                    matrizXor[a][b] =x;//asignación del promedio a la matrizSuma
                }
                //en otro caso se le asigna a la matrizSuma x
                else{
                    matrizXor[a][b] =x;//asignacion del pixel sumado a la matrizSuma
                }
                xor.putPixel(a,b, matrizXor[a][b]);
                imgGuardada.putPixel(a,b, matrizXor[a][b]);
             }   
        }
        
   
        new ImagePlus("Xor",xor).show();
    }

  
public void sacarSuma(){
        // leerMatrizOperaciones();
          File file, copia;
        
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo PGM(.pgm)", "pgm");
        JFileChooser selector=new JFileChooser();
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Abrir archivo...");
        File ruta = new File("proyecto");
        selector.setCurrentDirectory(ruta);
        int resultado=selector.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
          file=selector.getSelectedFile();
           copia=new File(file.getAbsolutePath());
           String nombre=String.valueOf(copia);
        //Creación del objeto op, se utiliza para 
	Opener op = new Opener();
        //- imagenO:  se guarda la imagen utilizando la ruta guardada en la variable nombre
        System.out.println(nombre);
        imagenOper = op.openImage(nombre);
        /* Se obtien las medidas de la matriz
         - ancho: se guarda el numero de columnas de la imagen
         - alto: se guarda el numero de filas de la imagen*/
        anchoOper=imagenOper.getWidth();
        altoOper=imagenOper.getHeight();
        //Creación del objeto matriz, se utiliza para guardar los pixeles de la imagen
        matrizOperaciones=new int[anchoOper][altoOper];
      //  matrizSegmentacion=new int [ancho][alto];
        //Variable pixel para la obtención de los pixeles de la imagen
        int pixel;
        //Creación de una imagen  apartir de la obtenida en el objeto imagenO
        ImageProcessor ip=imagenOper.getProcessor();
        /* Los siguientes dos for se obtiene los pixeles de la imagen en tipo int, 
         * estos se guardan en la variable pixel con getPixel y se asignan
         * a la matriz para realizar las operaciones correspondientes 
         * (las vistas en la materia de Tratamiento de Imagenes)
         * donde:
         * a; es un índice que controla el ancho de la imagen este va de [0,ancho]
         * b; es un inidce que controla el alto de la imagen este va de [0,alto]
         * La intersección de los índices nos da el pixel*/
        for (int a=0; a<anchoOper; a++) {
            for (int b=0; b<altoOper; b++){
                pixel = ip.getPixel(a, b);
                matrizOperaciones[a][b] = pixel;
            }
        }
        /* Con el método open se abre la imagen a la que se le va hacer las 
         * operaciones correpondientes solo mandando la ruta con la variable nombre*/
        op.open(nombre);
        }
        //en este método se obtiene la ruta de la segunda imagen
        /*si ancho de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al ancho de la segunda iamgen*/
        System.out.println(anchoOper);
        if(ancho>anchoOper){
         ancho=anchoOper;
       }
        /*si alto de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al alto de la segunda iamgen*/
        if(alto>altoOper){
          alto=altoOper;
       }
        System.out.println(anchoOper);
        System.out.println(altoOper);
        System.out.println(ancho);
        System.out.println(ancho);
         /*Creación de una nueva imagen llamada "or" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
         or= null;
         or =  new ByteProcessor(ancho,alto);
        /*Declaración de la matriz que se utilizara para gurdar los valores 
         * alterados por las operaciones de la matriz original*/
        int matrizOr[][]=new int [ancho][alto];
       /*En los siguientes dos ciclos se va recorriendo la matriz para poder 
       * asignar el valor de cada posicion a la matriz de copia, para poder sacar
       * la matriz original.
       *    Esto  se hace mediante la instrucción:
       *        x=matriz[a][b]|matrizOperaciones[a][b];
       * donde: 
       *    a E[0,ancho de la matriz]
       *    b E[0,alto de la matriz]
       *    x = se le asigna el valor del pixel de la matrices con el operando or
       * Nota:
       *    para poder realizar or, se tuvo que poner las siguientes condiciones,
       *    para que los niveles de gris fuara del rango de 0 a 255:
       *    si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizSuma
       *    en otro caso se le asigna a la matrizSuma x
       * La matriz copia se asigna a la imagen "suma" mediante el metodo putPixel, 
       * el cual tiene como parametros laposicion del pixel y el valor del pixel*/
        int x=0;
        double d=0;
        for (int a=0; a<ancho; a++) {
            for (int b=0; b<alto; b++){
                       
                x=matriz[a][b] + matrizOperaciones[a][b];
                
                       
                        
               // si x>255 entonces se realiza promedio (x/2) y se le asigna a la matrizSuma
             if(x>255){
                    x=x/2;//promedio de los pixeles que es la suma de los pixeles entre dos
                    matrizOr[a][b] =x;//asignación del promedio a la matrizSuma
                }
                //en otro caso se le asigna a la matrizSuma x
                else{
                    matrizOr[a][b] =x;//asignacion del pixel sumado a la matrizSuma
                }
                or.putPixel(a,b, matrizOr[a][b]);
                imgGuardada.putPixel(a,b, matrizOr[a][b]);
             }   
        }
        
   
       //new ImagePlus("Suma de imágenes ",or).show();
       new ImagePlus("Suma de imágenes ",or);
    }
public void sacarResta(){
         File file, copia;
        
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo PGM(.pgm)", "pgm");
        JFileChooser selector=new JFileChooser();
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Abrir archivo...");
        File ruta = new File("tarea2");
        selector.setCurrentDirectory(ruta);
        int resultado=selector.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
          file=selector.getSelectedFile();
           copia=new File(file.getAbsolutePath());
           String nombre=String.valueOf(copia);
        //Creación del objeto op, se utiliza para 
	Opener op = new Opener();
        //- imagenO:  se guarda la imagen utilizando la ruta guardada en la variable nombre
        System.out.println(nombre);
        imagenOper = op.openImage(nombre);
        /* Se obtien las medidas de la matriz
         - ancho: se guarda el numero de columnas de la imagen
         - alto: se guarda el numero de filas de la imagen*/
        anchoOper=imagenOper.getWidth();
        altoOper=imagenOper.getHeight();
        //Creación del objeto matriz, se utiliza para guardar los pixeles de la imagen
        matrizOperaciones=new int[anchoOper][altoOper];
      //  matrizSegmentacion=new int [ancho][alto];
        //Variable pixel para la obtención de los pixeles de la imagen
        int pixel;
        //Creación de una imagen  apartir de la obtenida en el objeto imagenO
        ImageProcessor ip=imagenOper.getProcessor();
        /* Los siguientes dos for se obtiene los pixeles de la imagen en tipo int, 
         * estos se guardan en la variable pixel con getPixel y se asignan
         * a la matriz para realizar las operaciones correspondientes 
         * (las vistas en la materia de Tratamiento de Imagenes)
         * donde:
         * a; es un índice que controla el ancho de la imagen este va de [0,ancho]
         * b; es un inidce que controla el alto de la imagen este va de [0,alto]
         * La intersección de los índices nos da el pixel*/
        for (int a=0; a<anchoOper; a++) {
            for (int b=0; b<altoOper; b++){
                pixel = ip.getPixel(a, b);
                matrizOperaciones[a][b] = pixel;
            }
        }
        /* Con el método open se abre la imagen a la que se le va hacer las 
         * operaciones correpondientes solo mandando la ruta con la variable nombre*/
        op.open(nombre);
        }
        //en este método se obtiene la ruta de la segunda imagen
        /*si ancho de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al ancho de la segunda iamgen*/
        if(ancho>anchoOper){
           ancho=anchoOper;
        }
        /*si alto de la primera imagen es mayor entonces ancho de la primera imagen 
        es igual al alto de la segunda iamgen*/
        if(alto>altoOper){
            alto=altoOper;
        }
        /*Creación de una nueva imagen llamada "resta" en blanco con las mismas 
        * medidas de la imagen que se desea procesar, ya que para mostrar la 
        * nueva imagen se desea mantener la anterio sin modificaciones y mostrar
        * las 2 en diferentes ventanas*/
        resta= null;
        resta =  new ByteProcessor(ancho,alto);
        /*Declaración de la matriz que se utilizara para gurdar los valores 
         * alterados por las operaciones de la matriz original*/
        int matrizResta[][]=new int [ancho][alto];
       /*En los siguientes dos ciclos se va recorriendo la matriz para poder 
       * asignar el valor de cada posicion a la matriz de copia, para poder sacar
       * la resta de la matriz original.
       *    Ésto  se hace mediante la instrucción:
       *        x=matriz[a][b]-matriz[a][b];
       * donde: 
       *    a E[0,ancho de la matriz]
       *    b E[0,alto de la matriz]
       *    x = se le asigna el valor del pixel de la matrices restadas
       * Nota:
       *    para poder realizar la resta, se tuvo que poner las siguientes condiciones,
       *    para que los niveles de gris fuerna del rando de 0 a 255:
       *    si x<0 entonces se le asigna a la matrizResta 0
       *    en otro caso se le asigna a la matrizResta x
       * La matriz copia se asigna a la imagen "resta" mediante el metodo putPixel, 
       * el cual tiene como parametros laposicion del pixel y el valor del pixel*/
        int x=0;
        double d=0;
        for (int a=0; a<ancho; a++) {
            for (int b=0; b<alto; b++){
                //resta de las matrices de las dos imagenes
                x=matriz[a][b]-matrizOperaciones[a][b];
                //si x<0 entonces se le asigna a la matrizResta 0
                if(x<0){
                    matrizResta[a][b] =0;
                }
                //en otro caso se le asigna a la matrizResta la resta de los pixeles almacenada en x
                else{
                    matrizResta[a][b] =x;
                }
                resta.putPixel(a,b, matrizResta[a][b]);
                         imgGuardada.putPixel(a,b, matrizResta[a][b]);
             }   
        }
        
         /*Con este metodo se manda a imprimir la imagen modificada por las operaciones*/
       // new ImagePlus("Resta de imágenes",resta).show();
        new ImagePlus("Resta de imágenes",resta);
    }
public void guardar() {
        //se verifica que la imagen haya sido seleccionada
        String[] types = {"pgm","png","bmp","pict","jpeg","xbm","tga","psd","xpm","pcx","ico"};
	String type = "png";        
        if (imgGuardada == null) {
            JOptionPane.showMessageDialog(null, "No hay imagen",
                    "Error no se encontro imagen", JOptionPane.ERROR_MESSAGE);
        } else {
            GenericDialog gd = new GenericDialog("Cris", IJ.getInstance());
            gd.addChoice("Format:", types, type);
            gd.showDialog();
            if (gd.wasCanceled());
            //return;
            type = gd.getNextChoice();
            //se manada a llamar una cuadro de dialogo para guardarla
            SaveDialog sd = new SaveDialog("Guardar", "untitled", null);
            
            //se obtiene la direccioón de donde se va a guardar la imagen
            String dir = sd.getDirectory();
            if (null == dir) {
                return; // user canceled dialog  
            }
            dir = dir.replace('\\', '/'); // Windows safe  
            //se verifica si la direccion termina con /, si no se la agrega
            if (!dir.endsWith("/")) {
                dir += "/";
            }
            //se obtiene el nombre de la imagen a guardar
            String nom = sd.getFileName();
            //se inicializa el objeto de tipo imagen
            if(banBit==0){
               ImagePlus i = new ImagePlus(nom, imgGuardada);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i, type, dir + nom);
                //se cierra el objeto imagen
                JOptionPane.showMessageDialog(null, "Imagen guardada");
                i.close(); 
            }
            else{
                ImagePlus i = new ImagePlus(nom, imgBit1);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i, type, dir + nom);
                
                ImagePlus i1 = new ImagePlus(nom+"1", imgBit2);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i1, type, dir + nom+"1");
                               
                ImagePlus i2 = new ImagePlus(nom+"2", imgBit3);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i2, type, dir + nom+"2");
                
                ImagePlus i3 = new ImagePlus(nom+"3", imgBit4);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i3, type, dir + nom+"3");
                                
                ImagePlus i4 = new ImagePlus(nom+"4", imgBit5);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i4, type, dir + nom+"4");
                //se cierra el objeto imagen
                
                ImagePlus i5 = new ImagePlus(nom+"5", imgBit6);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i5, type, dir + nom+"5");
                //se cierra el objeto imagen
                
                ImagePlus i6 = new ImagePlus(nom+"6", imgBit7);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i6, type, dir + nom+"6");
                //se cierra el objeto imagen
                
                ImagePlus i7 = new ImagePlus(nom+"7", imgBit8);
                //se guarda la imagen, dando ruta, dirección y formato
                IJ.saveAs(i7, type, dir + nom+"7");
                banBit=0;
                JOptionPane.showMessageDialog(null, "Imagen guardada");
                i.close();
                i1.close();
                i2.close();
                i3.close();
                i4.close();
                i5.close();
                i6.close();
                i7.close();
                
            }
            
        }
    }
}


