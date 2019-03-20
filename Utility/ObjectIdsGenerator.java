package Utility;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectIdsGenerator implements Serializable{
	
	private static final long serialVersionUID = -8672880699759726440L;
	
	private AtomicInteger ID_LV = new AtomicInteger(1);
	private AtomicInteger ID_VL = new AtomicInteger(1);
	 
    private ObjectIdsGenerator() {
    }
 
    public static ObjectIdsGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public int getAndIncrementIdLv() {
    	int id = ID_LV.getAndIncrement();
    	ObjectPersistance.saveObject(this, 1); 
    	return id;
	}
    
    public int getAndIncrementIdVl() { 
    	int id = ID_VL.getAndIncrement();
    	ObjectPersistance.saveObject(this, 1);
    	return  id;
	}
 
    private static class SingletonHolder {
        private static final ObjectIdsGenerator INSTANCE = ObjectPersistance.getObject(ObjectIdsGenerator.class, 1) == null ? 
        		new ObjectIdsGenerator() : (ObjectIdsGenerator)ObjectPersistance.getObject(ObjectIdsGenerator.class, 1);
    }
}
