package sp.action.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.util.Constant;
/**
 * Transfer Upload File :using BlobStore
 * @author Thuy
 *
 */
public class TransferUploadFile extends Action{
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
            {
            return mapping.findForward(Constant.SUCCESS);
    	}
}
