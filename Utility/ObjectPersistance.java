package Utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ObjectPersistance {
	private static final String DIR_PATH_STR = "ObjectSorage/";
	
	public static boolean saveObject(Object o, int id){
	    String fileName = DIR_PATH_STR + o.getClass().getSimpleName() + id;
	    try {
	    	FileOutputStream fos = new FileOutputStream(fileName);
		    ObjectOutputStream oos = new ObjectOutputStream(fos); 	    
		    oos.writeObject(o);
		    oos.close();
	    }
	    catch (IOException e) { e.printStackTrace(); return false;}
	    return true;
	}

	public static Object getObject(Class<?> c, int id){
		String fileName = DIR_PATH_STR + c.getSimpleName() + id;
		Object o = null;
		try {
		    FileInputStream fin = new FileInputStream(fileName);
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    o = ois.readObject();
		    ois.close();
		}
		catch (ClassNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace(); }
		return o;
	}
	
	public static <T> List<T> getObjectsOfClass(Class<T> c) {
		List<T> objects = new ArrayList<>();
		try {
			Files.walk(Paths.get(DIR_PATH_STR))
			 .filter( p -> (p.getFileName().toString().contains(c.getSimpleName()) && Files.isRegularFile(p)) )
			 .forEach(p -> { 
				 FileInputStream fin;
				try {
					fin = new FileInputStream(p.toString());
					ObjectInputStream ois = new ObjectInputStream(fin);
					objects.add(c.cast(ois.readObject())); 
					ois.close();
				} 
				catch (IOException e) { e.printStackTrace(); }
				catch (ClassNotFoundException e) { e.printStackTrace();}
			 });
			 //.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public static void removeObject(Class<?> c, int id){

        try {
			Files.deleteIfExists(Paths.get(DIR_PATH_STR + c.getSimpleName() + id));
			//System.out.println("Deletion successful.");
		}
        catch(NoSuchFileException e) 
        { 
            System.out.println("ERROR: No such file/directory exists");
            e.printStackTrace();
        } 
        catch(IOException e) 
        { 
            System.out.println("ERROR: Invalid permissions."); 
            e.printStackTrace();
        }
	}
}
