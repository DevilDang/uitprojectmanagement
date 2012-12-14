package sp.action;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
    
    	Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
    	BlobKey blobKey = blobs.get("myFile");
System.out.println("123");
    	if (blobKey == null) {
    		return mapping.findForward("success");
    	} else {
    		//get key cua file upload
    		System.out.println(blobKey.getKeyString());
    		HttpSession se = request.getSession();
    		se.setAttribute("pic", blobKey.getKeyString());
    		//upload thanh cong, ghi vo DB
    		
    		//forward den trang tiep theo
    		
    		return mapping.findForward("success");
    	}
    }
}
