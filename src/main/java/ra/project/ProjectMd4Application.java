package ra.project;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProjectMd4Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMd4Application.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT); // yêu cầu map chính xác tên truờng
        return modelMapper;
    }
    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "cloudName",
                "api_key", "apiKey",
                "api_secret", "apiSecret",
                "secure", true)); // Nên dùng HTTPS
    }
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Eventos API")
                .description("API RESTful para gerenciamento de membros, grupos e convidados.").version("1.0.0")
                .contact(new Contact().name("Samuel Maciel da Silva").email("samuelmsilva@outlook.com.br")
                        .url("https://github.com/samuelmsilva2v"))
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }


}
