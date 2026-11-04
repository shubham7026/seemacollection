package com.seemacollection.seemacollection.commadnlinerunner;

import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.entity.UserAuthEntity;
import com.seemacollection.seemacollection.exception.AbstractException;
import com.seemacollection.seemacollection.repository.UserAuthRepository;
import com.seemacollection.seemacollection.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LoadH2Data implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try{
            log.info("Adding products in H2 database...");
            getProducts().forEach(product -> {
                productService.persistProduct(product,false);
            });
            log.info("Products added");

            log.info("deleting existing users..");
            userAuthRepository.deleteAll();
            log.info("users deleted");

            log.info("Add Users");
            userAuthRepository.saveUser(
                    List.of(
                            UserAuthEntity.builder().username("shubham").enabled(true).password(bCryptPasswordEncoder.encode("shubham")).role("ROLE_admin").build(),
                            UserAuthEntity.builder().username("root").enabled(true).password(bCryptPasswordEncoder.encode("root")).role("ROLE_admin").build()
                    )
            );
            log.info("users added");
        }catch (Exception e){
            throw new AbstractException("Unabled to load reference data because: {}" + e.getCause(), HttpStatus.CONFLICT);
        }


    }

    private List<ProductDTO> getProducts (){
        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(new ProductDTO("HairOil", "Parachute Hair Oil", 50D, 20L, "HairCare"));
        productDTOS.add(new ProductDTO("Shampoo", "Head & Shoulders Anti-Dandruff", 150D, 40L, "HairCare"));
        productDTOS.add(new ProductDTO("Conditioner", "Dove Intense Repair", 120D, 35L, "HairCare"));
        productDTOS.add(new ProductDTO("HairSerum", "L'Oreal Paris Extraordinary", 299D, 15L, "HairCare"));
        productDTOS.add(new ProductDTO("HairGel", "Set Wet Styling Gel", 60D, 50L, "HairCare"));
        productDTOS.add(new ProductDTO("FaceWash", "Himalaya Herbals Purifying Neem", 85D, 100L, "SkinCare"));
        productDTOS.add(new ProductDTO("Moisturizer", "Nivea Soft Light", 149D, 60L, "SkinCare"));
        productDTOS.add(new ProductDTO("Sunscreen", "Lotus Herbals Safe Sun", 315D, 25L, "SkinCare"));
        productDTOS.add(new ProductDTO("BodyLotion", "Vaseline Intensive Care", 245D, 45L, "SkinCare"));
        productDTOS.add(new ProductDTO( "FaceScrub", "Everyuth Naturals Walnut", 110D, 30L, "SkinCare"));
        productDTOS.add(new ProductDTO( "Notebook", "Classmate A4 Ruled", 65D, 120L, "Stationery"));
        productDTOS.add(new ProductDTO( "GelPen", "Reynolds Trimax Blue", 45D, 200L, "Stationery"));
        productDTOS.add(new ProductDTO( "Highlighter", "Faber-Castell Textliner", 25D, 150L, "Stationery"));
        productDTOS.add(new ProductDTO( "StickyNotes", "Post-it 3x3 Yellow", 90D, 80L, "Stationery"));
        productDTOS.add(new ProductDTO( "GeometryBox", "Camel Scholar", 120D, 40L, "Stationery"));
        productDTOS.add(new ProductDTO( "Smartphone", "OnePlus Nord 5 5G", 29999D, 10L, "Electronics"));
        productDTOS.add(new ProductDTO( "Laptop", "ASUS Vivobook S14", 65000D, 5L, "Electronics"));
        productDTOS.add(new ProductDTO( "Earbuds", "boAt Airdopes 141", 1299D, 50L, "Electronics"));
        productDTOS.add(new ProductDTO( "Smartwatch", "Noise ColorFit Pro", 2499D, 30L, "Electronics"));
        productDTOS.add(new ProductDTO( "PowerBank", "Mi 10000mAh", 1099D, 40L, "Electronics"));
        productDTOS.add(new ProductDTO( "PenDrive", "SanDisk Cruzer Blade 64GB", 450D, 100L, "Electronics"));
        productDTOS.add(new ProductDTO( "Mouse", "Logitech B170 Wireless", 595D, 60L, "Electronics"));
        productDTOS.add(new ProductDTO( "Keyboard", "HP 150 Wired", 499D, 55L, "Electronics"));
        productDTOS.add(new ProductDTO( "Monitor", "Samsung 24-inch IPS", 9500D, 12L, "Electronics"));
        productDTOS.add(new ProductDTO( "Router", "TP-Link Archer C6", 2199D, 20L, "Electronics"));
        productDTOS.add(new ProductDTO( "Rice", "India Gate Basmati 5kg", 495D, 30L, "Groceries"));
        productDTOS.add(new ProductDTO( "WheatFlour", "Aashirvaad Atta 5kg", 220D, 50L, "Groceries"));
        productDTOS.add(new ProductDTO( "Sugar", "Madhur Pure 1kg", 55D, 100L, "Groceries"));
        productDTOS.add(new ProductDTO( "Tea", "Red Label Natural Care 500g", 280D, 45L, "Groceries"));
        productDTOS.add(new ProductDTO( "Coffee", "Nescafe Classic 100g", 310D, 35L, "Groceries"));
        productDTOS.add(new ProductDTO( "Biscuit", "Parle-G Original 800g", 85D, 120L, "Groceries"));
        productDTOS.add(new ProductDTO( "Noodles", "Maggi 2-Minute Masala", 140D, 80L, "Groceries"));
        productDTOS.add(new ProductDTO( "CookingOil", "Fortune Sunflower 1L", 165D, 70L, "Groceries"));
        productDTOS.add(new ProductDTO( "Ketchup", "Kissan Fresh Tomato 1kg", 135D, 65L, "Groceries"));
        productDTOS.add(new ProductDTO( "Salt", "Tata Salt 1kg", 25D, 200L, "Groceries"));
        productDTOS.add(new ProductDTO( "TShirt", "Allen Solly Solid Polo", 899D, 25L, "Clothing"));
        productDTOS.add(new ProductDTO( "Jeans", "Levi's Men 511 Slim Fit", 1999D, 15L, "Clothing"));
        productDTOS.add(new ProductDTO( "Shirt", "Peter England Formal Blue", 1199D, 20L, "Clothing"));
        productDTOS.add(new ProductDTO( "Jacket", "Puma Men's Track", 2499D, 10L, "Clothing"));
        productDTOS.add(new ProductDTO( "Shorts", "Adidas Men's Running", 999D, 30L, "Clothing"));
        productDTOS.add(new ProductDTO( "Bedsheet", "Bombay Dyeing Double Cotton", 850D, 25L, "Home"));
        productDTOS.add(new ProductDTO( "Towel", "Trident Bath Towel", 450D, 40L, "Home"));
        productDTOS.add(new ProductDTO( "WallClock", "Ajanta Quartz", 350D, 35L, "Home"));
        productDTOS.add(new ProductDTO( "WaterBottle", "Milton Thermosteel 1L", 895D, 50L, "Home"));
        productDTOS.add(new ProductDTO( "LunchBox", "Cello Max Fresh", 420D, 60L, "Home"));
        productDTOS.add(new ProductDTO( "RunningShoes", "Nike Revolution 6", 3695D, 12L, "Footwear"));
        productDTOS.add(new ProductDTO( "CasualSneakers", "Puma Smash v2", 2199D, 18L, "Footwear"));
        productDTOS.add(new ProductDTO( "Sandals", "Sparx Men's Floater", 699D, 45L, "Footwear"));
        productDTOS.add(new ProductDTO( "Slippers", "Bata Comfit", 399D, 60L, "Footwear"));
        productDTOS.add(new ProductDTO( "FormalShoes", "Hush Puppies Leather", 2999D, 15L, "Footwear"));
        return productDTOS;
    }
}
