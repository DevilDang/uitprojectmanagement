package sp.action.report;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.util.Constant;

import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
/**
 * Upload File :using BlobStore
 * @author Thuy
 *
 */
public class UploadFile extends Action{
	/** . declare BlobstoreService */
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
            {
    
    	Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);	
    	List<BlobKey> blobKey = blobs.get("myFile");	
System.out.println("123");		
    	if (blobKey == null) {	
    		return mapping.findForward("success");
    	} else {	
    		//get key cua file upload
    		System.out.println(blobKey.get(0).getKeyString());
    		HttpSession se = request.getSession();
    		//save name into session
    		BlobInfoFactory bi = new BlobInfoFactory();
            String fname = bi.loadBlobInfo(blobKey.get(0)).getFilename();
    		se.setAttribute(Constant.REPORT_FILE_NAME, fname);
    		//save key into session
    		se.setAttribute(Constant.REPORT_FILE_ID, blobKey.get(0).getKeyString());
    		
    		return mapping.findForward("success");
    	}
    }
}
