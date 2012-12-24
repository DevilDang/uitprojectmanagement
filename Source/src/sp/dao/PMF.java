package sp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import sp.util.Constant;

/**
 * PersistenceManagerFactory class.
 * @author 
 *
 */
public final class PMF {
	/**
     * PersistenceManagerFactory object.
     */
    public static final PersistenceManagerFactory pmfInstance = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    /**
     * Default constructor.
     */
    private PMF() {
    }

    /**
     * get PersistenceManagerFactory.
     * @return PersistenceManagerFactory
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }

    /**
     * Get PersistenceManager.
     * @return PersistenceManager
     */
    public static PersistenceManager getPMF() {
        return get().getPersistenceManager();
    }
    
    /**
     * get obj by id
     * @param className object type
     * @param key primary key of object
     * @return Object
     */
    public static Object getObjectById(Class<?> className, Long key) {
        PersistenceManager pm = getPMF();
        Object obj = null;
        try {

            obj = pm.getObjectById(className, key);
        } catch (JDOObjectNotFoundException e) {
            return null;
        } finally {
            pm.close();
        }
        return obj;
    }
    
    /**
     * get ListObject
     * @param className object type
     * @param key primary key of object
     * @return List<Object>
     */
    @SuppressWarnings("unchecked")
	public static List<?> getList(Class<?> className) {
        PersistenceManager pm = getPMF();
        List<Object> tempList = new ArrayList<Object>();
        List<Object> resultList = new ArrayList<Object>();
        Query query = pm.newQuery(className);
        try {
        	
        	tempList = (List<Object>) query.execute();
        	resultList = (List<Object>) pm.detachCopyAll(tempList);    
        } catch (JDOObjectNotFoundException e) {
            return null;
        } finally {
        	query.closeAll();
            pm.close();    
        }
        return resultList;
    }
    
    /**
     * get ListObject by value
     * @param className Class<?> 
     * @param col String
     * @param value String
     * @return List<Object>
     */
    @SuppressWarnings("unchecked")
	public static List<Object> getListByValue(Class<?> className, String col, String value) {
    	PersistenceManager pm = getPMF();
        Query query = pm.newQuery(className);
        List<Object> results = null;
        List<Object> detachedList = null;
        query.setFilter(col + " == param");

        query.declareParameters("String param");
        try {
            detachedList = (List<Object>) query.execute(value);
            results = (List<Object>) pm.detachCopyAll(detachedList);
        } finally {
            query.closeAll();
            pm.close();
        }
        return results;
    }
    
    /**
     * get ListObject by value
     * @param className Class<?> 
     * @param col String
     * @param value Date
     * @return List<Object>
     */
    @SuppressWarnings("unchecked")
    public static List<?> getObjectListByValue(Class<?> className, String col, Date value) {
        PersistenceManager pm = getPMF();
        Query query = pm.newQuery(className);
        List<Object> results = null;
        List<Object> detachedList = null;
        query.setFilter(col + " == param");

        query.declareParameters("Date param");
        try {
            detachedList = (List<Object>) query.execute(value);
            results = (List<Object>) pm.detachCopyAll(detachedList);
        } finally {
            query.closeAll();
            pm.close();
        }
        return results;
    }
    
    
    
    /**
     * Kiem tra su ton tai cua object trong database.
     * @param className object type
     * @param key primary key of object
     * @return boolean
     */
    public static boolean isObject(Class<?> className, String key) {
        PersistenceManager pm = getPMF();
        try {

            pm.getObjectById(className, key);
        } catch (JDOObjectNotFoundException e) {
            return false;
        } finally {
            pm.close();
        }
        return true;
    }
    
    /**
     * get object
     * @param className object type
     * @param key primary key of object
     * @return Object
     */
    public static Object getObject(Class<?> className, String key) {
        PersistenceManager pm = getPMF();
        Object result = null; 
        try {

            result = pm.getObjectById(className, key);
        } catch (JDOObjectNotFoundException e) {
            return null;
        } finally {
            pm.close();
        }
        return result;
    }
    
    /**
     * get object
     * @param className object type
     * @param key primary key of object
     * @return Object
     */
    public static Object getObject(Class<?> className, Long key) {
        PersistenceManager pm = getPMF();
        Object result = null; 
        try {

            result = pm.getObjectById(className, key);
        } catch (JDOObjectNotFoundException e) {
            return null;
        } finally {
            pm.close();
        }
        return result;
    }
    
    
    /**
     * insert object
     * @param obj Object
     * @return boolean
     */
    public static boolean insertObject(Object obj) {
        PersistenceManager pm = getPMF();
        try {

            pm.makePersistent(obj);
        } catch (JDOObjectNotFoundException e) {
            return false;
        } finally {
            pm.close();
        }
        return true;
    }

    /**
     * delete object
     * @param className object type
     * @param key primary key of object
     * @return Object
     */
    public static boolean deleteObject(Class<?> className, Long key) {
        PersistenceManager pm = getPMF();
        Object obj = null;
        try {
        	obj = pm.getObjectById(className, key);
        	if(obj != null){
        		pm.deletePersistent(obj);
        	}
        	else
        	{
        		return false;
        	}
        } catch (JDOObjectNotFoundException e) {
            return false;
        } finally {
            pm.close();
        }
        return true;
    }
    
    // hàm này dùng để xóa các đối tượng mà key là 1 chuỗi  - Po
    public static boolean deleteObject(Class<?> className, String key) {
        PersistenceManager pm = getPMF();
        Object obj = null;
        try {
        	obj = pm.getObjectById(className, key);
        	if(obj != null){
        		pm.deletePersistent(obj);
        	}
        	else
        	{
        		return false;
        	}
        } catch (JDOObjectNotFoundException e) {
            return false;
        } finally {
            pm.close();
        }
        return true;
    }
    
    /*
     * get so luong record theo tung page
     */
    @SuppressWarnings("unchecked")
	public static List<?> getObjectList(Class<?> className, int page){
    	PersistenceManager pm = getPMF();
        Query query = pm.newQuery(className);
        query.setRange(Constant.RECORD * (page - 1), Constant.RECORD * page);
        List<Object> results = null;
        List<Object> detachedList = null;
        try {
            detachedList = (List<Object>) query.execute();
            results = (List<Object>) pm.detachCopyAll(detachedList);

        } finally {
            query.closeAll();
            pm.close();
        }
        return results;
        
    }
    
    /*
     * get so luong record theo tung page, filter
     */
    @SuppressWarnings("unchecked")
	public static List<?> getObjectList(Class<?> className, String filter, int page){
    	PersistenceManager pm = getPMF();
        Query query = pm.newQuery(className);
        
        //set filter
        query.setFilter(filter);
        
        //set range data
        query.setRange(Constant.RECORD * (page - 1), Constant.RECORD * page);
      
        List<Object> results = null;
        List<Object> detachedList = null;
        try {
            detachedList = (List<Object>) query.execute();
            results = (List<Object>) pm.detachCopyAll(detachedList);

        } finally {
            query.closeAll();
            pm.close();
        }
        return results;
        
    }
    
    
    /*
     * tinh tong so luong record co trong 1 table
     */
    @SuppressWarnings("unchecked")
	public static int countNumberAll(Class<?> className){
    	int number = 0;
    	PersistenceManager pm = getPMF();
        List<Object> tempList = new ArrayList<Object>();
        Query query = pm.newQuery(className);
        try {
        	
        	tempList = (List<Object>) query.execute();
        	if (tempList != null){
        	number = tempList.size();
        	}
        } catch (JDOObjectNotFoundException e) {
            return 0;
        } finally {
        	query.closeAll();
            pm.close();    
        }
        return number;
    	
    }
    
}
