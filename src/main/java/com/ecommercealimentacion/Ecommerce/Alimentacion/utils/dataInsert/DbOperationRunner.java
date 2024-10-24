package com.ecommercealimentacion.Ecommerce.Alimentacion.utils.dataInsert;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Category;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.Product;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.entities.User;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.enums.Role;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.ICategoryRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.IProductRepository;
import com.ecommercealimentacion.Ecommerce.Alimentacion.repositories.UserRepository;


@Component
public class DbOperationRunner implements CommandLineRunner {

     public ICategoryRepository categoryRepository;

     public IProductRepository productRepository;

     public UserRepository userRepository;

     public PasswordEncoder passwordEncoder;



    public DbOperationRunner (ICategoryRepository categoryRepository,IProductRepository productRepository,PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void run(String... args) throws Exception {


        User userInvitado = new User();
        userInvitado.setUsername("Invitado");
        userInvitado.setEmail("invitado@gmail.com");
        userInvitado.setPassword(passwordEncoder.encode("Invitado123"));
        userInvitado.setRole(Role.CUSTOMER);

        // Creacion usuario ADMIN.
        User userAdmin = new User();
        userAdmin.setUsername("Admin");
        userAdmin.setEmail("admin@gmail.com");
        userAdmin.setPassword(passwordEncoder.encode("ADMIN12345"));
        userAdmin.setRole(Role.ADMIN);

        userRepository.save(userInvitado);
        userRepository.save(userAdmin);


        // Creación de las categorías
        Category category1 = new Category("PROCESADORES");
        Category category2 = new Category("MOTHERBOARD");
        Category category3 = new Category("GABINETES");
        Category category4 = new Category("PERIFERICOS");
        Category category5 = new Category("FUENTES");

        // Guardar categorías en el repositorio
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);

        // Creación de productos para cada categoría
        Product product1 = new Product("Intel Core i7-11700K", BigDecimal.valueOf(350.00), category1,
                "https://i.postimg.cc/0NdqjXhT/I7.jpg");
        Product product2 = new Product("AMD Ryzen 5 5600X", BigDecimal.valueOf(300.00), category1,
                "https://i.postimg.cc/tCF0BCz3/RYZEN5.webp");
        Product product3 = new Product("Intel Core i5-11400F", BigDecimal.valueOf(200.00), category1,
                "https://i.postimg.cc/3rs5jD2r/I5.jpg");
        Product product4 = new Product("AMD Ryzen 7 5800X", BigDecimal.valueOf(400.00), category1,
                "https://i.postimg.cc/sfQCZJQ0/R7.webp");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        Product product5 = new Product("MSI B450 TOMAHAWK MAX", BigDecimal.valueOf(120.00), category2,
                "https://i.postimg.cc/J4rn3Z1V/MSIMOTHER.jpg");
        Product product6 = new Product("ASUS ROG STRIX B550-F", BigDecimal.valueOf(180.00), category2,
                "https://i.postimg.cc/J4php0ny/ASUSMOTHER.jpg");
        Product product7 = new Product("Gigabyte AORUS X570", BigDecimal.valueOf(250.00), category2,
                "https://i.postimg.cc/3x6xHq2Y/GIGAAORUS.png");
        Product product8 = new Product("ASRock B450M PRO4", BigDecimal.valueOf(100.00), category2,
                "https://i.postimg.cc/RZqZym6g/ASROCKMOTHER.png");

        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);

        Product product9 = new Product("NZXT H510", BigDecimal.valueOf(70.00), category3,
                "https://i.postimg.cc/Hxtmjv8J/NZXTGABINETE.jpg");
        Product product10 = new Product("Corsair 4000D Airflow", BigDecimal.valueOf(85.00), category3,
                "https://i.postimg.cc/nzxZFfrb/CORSAIRGABINETE.jpg");
        Product product11 = new Product("Cooler Master MasterBox Q300L", BigDecimal.valueOf(50.00), category3,
                "https://i.postimg.cc/rpxM2w8N/GABINETECOOLERMASTER.jpg");
        Product product12 = new Product("Lian Li PC-O11 Dynamic", BigDecimal.valueOf(140.00), category3,
                "https://i.postimg.cc/sgtsLhR2/liangabinete.jpg");

        productRepository.save(product9);
        productRepository.save(product10);
        productRepository.save(product11);
        productRepository.save(product12);

        Product product13 = new Product("Logitech G502 HERO", BigDecimal.valueOf(50.00), category4,
                "https://i.postimg.cc/rFZ8SHnx/Logitech-PERI.jpg");
        Product product14 = new Product("Razer BlackWidow V3", BigDecimal.valueOf(120.00), category4,
                "https://i.postimg.cc/nchF9xkn/RAZERPERI.jpg");
        Product product15 = new Product("HyperX Cloud II", BigDecimal.valueOf(90.00), category4,
                "https://i.postimg.cc/pVZVdrK0/HYPERXPERI.webp");
        Product product16 = new Product("SteelSeries QcK", BigDecimal.valueOf(25.00), category4,
                "https://i.postimg.cc/CxZwJ4Xt/STEELPADPERI.jpg");

        productRepository.save(product13);
        productRepository.save(product14);
        productRepository.save(product15);
        productRepository.save(product16);

        Product product17 = new Product("Corsair RM750x", BigDecimal.valueOf(130.00), category5,
                "https://i.postimg.cc/7hxxVrrM/FUENTECORSAIR.jpg");
        Product product18 = new Product("EVGA SuperNOVA 650 G5", BigDecimal.valueOf(110.00), category5,
                "https://i.postimg.cc/2y2C99zp/EVGAFUENTE.webp");
        Product product19 = new Product("Seasonic FOCUS GX-850", BigDecimal.valueOf(150.00), category5,
                "https://i.postimg.cc/26Nj1BHt/SEASOINICFUENTE.jpg");
        Product product20 = new Product("Cooler Master MWE Gold 750", BigDecimal.valueOf(100.00), category5,
                "https://i.postimg.cc/mD3LC2Jg/COOLERMFUENTE.jpg");

        productRepository.save(product17);
        productRepository.save(product18);
        productRepository.save(product19);
        productRepository.save(product20);

    }

}
