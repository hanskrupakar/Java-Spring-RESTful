package hans;

import javax.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.Types;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

    DataSource dataSource;
    
    private JdbcTemplate jdbcTemplate; 
    
    private String string;   
    
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/db");
	driverManagerDataSource.setUsername("root");
	driverManagerDataSource.setPassword("root");
	this.dataSource=driverManagerDataSource;
	return driverManagerDataSource;
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String checkCaptcha(@RequestParam("g-recaptcha-response") )
    {
    	String secret_key="6LfaBxUTAAAAACmVa6JSzuaQdgCnSsM1P8UNIkqy";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

	this.jdbcTemplate = new JdbcTemplate(dataSource);
	
    	try{
    	
    		this.string = jdbcTemplate.queryForObject("select enabled from users where username=?",new Object[]{personForm.getUsername()} ,String.class);
    	}
    	catch (EmptyResultDataAccessException e) {
		string=null;
	}
			
        if (bindingResult.hasErrors()) {
            return "form";
        }
        
        if(string!=null)
        {
            bindingResult.addError(new FieldError("AlreadyExists", "username", "Username already exists!"));
            return "form";
        }
        
        if(!(personForm.getP1().equals(personForm.getP2())))
        {
            bindingResult.addError(new FieldError("PasswordsDontMatch1", "p1", "Typed Passwords Don't Match!"));
            bindingResult.addError(new FieldError("PasswordsDontMatch2", "p2", "Typed Passwords Don't Match!"));
            return "form";
        }
	
	try{
	
	Object[] params1 = new Object[] { personForm.getUsername(), new BCryptPasswordEncoder(5).encode(personForm.getP1()),"1" };
	Object[] params2 = new Object[] { personForm.getUsername(), "ROLE_USER" };
	int[] types1 = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
	int[] types2 = new int[] { Types.VARCHAR, Types.VARCHAR };
	int row = jdbcTemplate.update("insert into users values(?,?,?)", params1, types1);
	int det = jdbcTemplate.update("insert into user_roles values(?,?)", params2, types2);		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
        return "redirect:/results";
    }
    
}
