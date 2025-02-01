package com.gardenia.viveroapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Converters.BaseCostFactory;
import com.gardenia.viveroapp.Converters.ImageFactory;
import com.gardenia.viveroapp.Converters.ProductFactory;
import com.gardenia.viveroapp.DTO.ImageDTO;
import com.gardenia.viveroapp.DTO.ProductDTO;
import com.gardenia.viveroapp.Model.BaseCost;
import com.gardenia.viveroapp.Model.Image;
import com.gardenia.viveroapp.Model.Product;
import com.gardenia.viveroapp.Repository.ImageRepository;
import com.gardenia.viveroapp.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageFactory imageFactory;

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private BaseCostFactory baseCostFactory;

    public List<ProductDTO> getProductsWithoutVariants() {
        List<Product> products = productRepository.findByParentIsNullAndChildrenIsEmpty();
        List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();

        if (productsDTO != null) {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                ProductDTO productDTO = productFactory.toDTO(product);
                productDTO.setBaseCost(baseCostFactory.toDTO(product.getBasecost()));
                List<Image> images = product.getImages();
                List<ImageDTO> imagesDTO = new ArrayList<ImageDTO>();
                for (int j = 0; j < images.size(); j++) {
                    Image image = images.get(j);
                    ImageDTO imageDTO = imageFactory.toDTO(image);
                    imagesDTO.add(imageDTO);
                }
                productDTO.setImages(imagesDTO);
                productsDTO.add(productDTO);

            }
        }
        return productsDTO;
    }

    public ProductDTO getProductById(Integer id) {
        List<ProductDTO> childrenDTO = new ArrayList<ProductDTO>();
        List<ImageDTO> imagesDTO = new ArrayList<ImageDTO>();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            List<Product> children = productRepository.findByParentIdproduct(id);
            List<Image> images = product.getImages();
            for (int i = 0; i < children.size(); i++) {
                Product productChildren = children.get(i);
                ProductDTO productChildrenDTO = productFactory.toDTO(productChildren);
                childrenDTO.add(productChildrenDTO);
            }
            for (int i = 0; i < images.size(); i++) {
                Image image = images.get(i);
                ImageDTO imageDTO = imageFactory.toDTO(image);
                imagesDTO.add(imageDTO);
            }
            ProductDTO productDTO = productFactory.toDTO(product);
            productDTO.setVariants(childrenDTO);
            productDTO.setImages(imagesDTO);
            productDTO.setBaseCost(baseCostFactory.toDTO(product.getBasecost()));
            return productDTO;
        } else {
            return null;
        }
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productFactory.DTOToEntity(productDTO);
        if (productDTO.getVariants() != null && productDTO.getVariants().size() != 0) {
            List<Product> productVariants = new ArrayList<Product>();
            List<ProductDTO> variants = productDTO.getVariants();
            for (ProductDTO variantDTO : variants) {
                Product productVariant = productFactory.DTOToEntity(variantDTO);
                BaseCost variantBaseCost = baseCostFactory.DTOToEntity(variantDTO.getBaseCost());
                productVariant.setBasecost(variantBaseCost);
                productVariant.setParent(product);
                productVariants.add(productVariant);
            }
            product.setChildren(productVariants);
        }
        BaseCost baseCost = baseCostFactory.DTOToEntity(productDTO.getBaseCost());
        product.setBasecost(baseCost);
        productRepository.save(product);
        return productDTO;
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getIdproduct());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDTO.getName());
            product.setAssociatedCost(productDTO.getAssociatedCost());
            product.setStock(productDTO.getStock());
            product.setDescription(productDTO.getDescription());
            BaseCost baseCost = baseCostFactory.DTOToEntity(productDTO.getBaseCost());
            product.setBasecost(baseCost);

            // SE ESTAN ELIMINANDO LAS VARIANTES Y VOLVIENDO A AGREGAR, esto puede evitarse
            // Con una llamada recursiva al metodo Update.
            List<Product> existingVariants = product.getChildren();
            existingVariants.clear();

            if (productDTO.getVariants() != null && !productDTO.getVariants().isEmpty()) {
                for (ProductDTO variantDTO : productDTO.getVariants()) {
                    Product productVariant = productFactory.DTOToEntity(variantDTO);
                    BaseCost variantBaseCost = baseCostFactory.DTOToEntity(variantDTO.getBaseCost());
                    productVariant.setBasecost(variantBaseCost);
                    productVariant.setParent(product);
                    existingVariants.add(productVariant);
                }
            }

            productRepository.save(product);
            return productFactory.toDTO(product);
        } else {
            return null;
        }
    }
}
