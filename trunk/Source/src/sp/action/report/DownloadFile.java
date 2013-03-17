
package sp.action.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * @author ThuyNT
 */
public class DownloadFile extends Action {
    /** . declare BlobstoreService */
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    /**
     * [ServeImage(Display Image dynamic)].
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws Exception Exception
     * @see ActionForward Struts1 Framework
     */

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String urlKey = request.getParameter("id");
        if (urlKey != null) {
            if (!urlKey.isEmpty()) {
                BlobKey blobKey = new BlobKey(urlKey);
                BlobInfoFactory bi = new BlobInfoFactory();
                String fname = bi.loadBlobInfo(blobKey).getFilename();
                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment; filename=" + fname);
               
                blobstoreService.serve(blobKey, response);
            }
        }
        return null;
    }



}
