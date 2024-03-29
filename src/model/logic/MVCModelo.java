package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;

import model.data_structures.Graph;
import model.data_structures.Queue;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;


/**
 * Definicion del modelo del mundo
 *
 */

public class MVCModelo<K> {

	private Graph<Integer,Informacion> grafo;

	private Vertex vertices;
	private Edge arcos;
	private Informacion infos;

	private Queue<Integer> ids = new Queue<>();
	private Queue<String> values = new Queue<>();

	public MVCModelo() throws Exception
	{
		ids=new Queue<>();
		values=new Queue<>();
	}

	public void cargarTxtHash() throws IOException
	{
		int _arcos=0;
		int _vertices=0;
		String rutaE="./data/bogota_arcos.txt";
		String rutaV="./data/bogota_vertices.txt";
		try{
			// Abre el archivo utilizando un FileReader
			FileReader reader = new FileReader(rutaE);
			// Utiliza un BufferedReader para leer por l�neas
			BufferedReader lector = new BufferedReader( reader );   
			// Lee l�nea por l�nea del archivo
			String linea = lector.readLine( );
			while(linea!=null)
			{
				// Parte la l�nea con cada coma
				String[] partes = linea.split( " " );
				for (int i = 1; i<partes.length;i++) {
					System.out.println(Arrays.toString(partes));
					arcos = new Edge(linea,partes[i],"0");
					System.out.println(arcos.toString());
				}
				_arcos++;
				linea=lector.readLine();
			}
			lector.close( );
			reader.close( );
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try{
			// Abre el archivo utilizando un FileReader
			FileReader reader = new FileReader(rutaV);
			// Utiliza un BufferedReader para leer por l�neas
			BufferedReader lector = new BufferedReader( reader );   
			// Lee l�nea por l�nea del archivo
			String linea = lector.readLine( );
			while(linea!=null)
			{
				// Parte la l�nea con cada coma
				String[] partes = linea.split( " " );
				infos = new Informacion(Double.parseDouble(partes[1]), Double.parseDouble(partes[2]), Integer.parseInt(partes[3]));
				vertices = new Vertex(infos, Integer.parseInt(partes[0]));
				_vertices++;
				linea=lector.readLine();
			}
			lector.close( );
			reader.close( );
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("La cantidad total de arcos es "+_arcos);
		System.out.println("La cantidad total de vertices es "+_vertices);
		// Cierra los lectores y devuelve el resultado
	}

	public void crearJson() {
		// TODO Auto-generated method stub

        Gson gson = new Gson();
        // Java objects to File
        try (FileWriter writer = new FileWriter("./data/data.json")) {
            gson.toJson(grafo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	public void leerJson(){
		Gson gson = new Gson();
		String path = "./data/data.json";
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(path));
			grafo = gson.fromJson(reader, Informacion.class);
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void cantidadComponentesConectados() {
		// TODO Auto-generated method stub
		grafo.CC();
		//usa los metodos de cc y dfs juntos
		System.out.println("Cantidad de componentes conexos: "+ grafo.ccn());
		
	}

	public void graficaGoogleMaps() {
		// TODO Auto-generated method stub
		
	}
}