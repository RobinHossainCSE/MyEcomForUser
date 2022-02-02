package com.example.myecomforuser.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myecomforuser.models.ProductModel;
import com.example.myecomforuser.models.EcomUser;
import com.example.myecomforuser.repos.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final String TAG = ProductViewModel.class.getSimpleName();
    private final ProductRepository repository = new ProductRepository();
    public MutableLiveData<List<String>> categoryListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ProductModel>> productListLiveData = new MutableLiveData<>();
    public MutableLiveData<ProductModel> productLiveData = new MutableLiveData<>();

    public ProductViewModel() {
        getCategories();
        getProducts();
    }

    private void getProducts() {
        repository.getAllProducts(items -> {
            productListLiveData.postValue(items);
        });
    }


    private void getCategories(){
        repository.getAllCategories(items -> categoryListLiveData.postValue(items));

    }

    private void getProductsByCategory(String category){
        repository.getAllProductsByCategory(category,items -> productListLiveData.postValue(items));
    }

    public void getProductById(String productId){
        repository.getProductByProductId(productId, productModel -> {
            productLiveData.postValue(productModel);

        });
    }




}
