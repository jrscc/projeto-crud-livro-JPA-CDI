import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.validator.routines.ISBNValidator;

@FacesValidator("isbnvalidador")
public class ISBNValidador implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String codigo = value.toString();
		
		        if (!ISBNValidator.getInstance().isValid(codigo)) {
		            FacesMessage mensagem = new FacesMessage("ISBN inválido.", "ISBN inválido.");
		            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		            throw new ValidatorException(mensagem);
		        }
		    }
	}
