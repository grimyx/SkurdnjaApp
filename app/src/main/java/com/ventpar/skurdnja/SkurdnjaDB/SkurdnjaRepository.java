package com.ventpar.skurdnja.SkurdnjaDB;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUser;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderDao;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUserDao;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.ProductFromSuborder;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.ProductFromSuborderDao;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Suborder;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.SuborderDao;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.ProductDao;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.UserDao;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class SkurdnjaRepository {

    private UserDao mUserDao;
    private ProductDao mProductDao;
    private OrderDao mOrderDao;
    private SuborderDao mSuborderDao;
    private OrderWithUserDao mOrderWithUserDao;
    private ProductFromSuborderDao mProductFromSuborderDao;
    private AppDatabase mAppDatabase;
    private Application mApplication;

    // Products data
    private LiveData<List<Product>> mAllProducts;

    // Users data
    private LiveData<List<User>> mAllUsers;

    // Orders data
    private LiveData<List<OrderWithUser>> mUndeliveredOrders;

    // OrderSuborder
    private LiveData<List<Suborder>> mOrderSuborder;

    public SkurdnjaRepository(Application application){
        mAppDatabase = AppDatabase.getDatabase(application);
        mUserDao = mAppDatabase.mUserDao();
        mProductDao = mAppDatabase.mProductDao();
        mOrderDao = mAppDatabase.mOrderDao();
        mSuborderDao = mAppDatabase.mSuborderDao();
        mOrderWithUserDao = mAppDatabase.mOrderWithUserDao();
        mProductFromSuborderDao = mAppDatabase.mProductFromSuborderDao();
        mAllProducts = mProductDao.getAll();

        mAllUsers = mUserDao.getAll();

        mUndeliveredOrders = mOrderWithUserDao.getUndeliveredOrders();

        mApplication = application;
    }

    // PRODUCT

    public LiveData<List<Product>> getAllProducts(){
        return mAllProducts;
    }

    public void insertProduct(Product product){
        new InsertProductAsyncTask(mProductDao).execute(product);
    }

    public void updateProduct(Product product){
        new UpdateProductAsyncTask(mProductDao).execute(product);
    }

    public void deleteProduct(Product product){
        new DeleteProductAsyncTask(mProductDao).execute(product);
    }


    private class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao mProductDao;

        private InsertProductAsyncTask(ProductDao productDao) {
            mProductDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mProductDao.addProduct(products[0]);
            return null;
        }
    }

    private class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao mProductDao;

        private UpdateProductAsyncTask(ProductDao productDao){
            mProductDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mProductDao.updateProduct(products[0]);
            return null;
        }
    }

    private class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao mProductDao;

        private DeleteProductAsyncTask(ProductDao productDao){
            mProductDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mProductDao.deleteProduct(products[0]);
            return null;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    // USER

    public LiveData<List<User>> getAllUsers() {return mAllUsers;}

    public void insertUser(User user){
       new InsertUserAsyncTask(mUserDao).execute(user);
    }

    public void deleteUser(User user){
       new DeleteUserAsyncTask(mUserDao).execute(user);
    }

    public void updateUser(User user){
        new UpdateUserAsyncTask(mUserDao).execute(user);
    }

    private class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{

        private UserDao mUserDao;

        InsertUserAsyncTask(UserDao dao){
            mUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.addUser(users[0]);
            return null;
        }
    }

    private class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mDao;

        UpdateUserAsyncTask(UserDao dao){
            mDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mDao.updateUser(users[0]);
            return null;
        }
    }

    private class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao mDao;

        DeleteUserAsyncTask(UserDao dao){
            mDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mDao.deleteUser(users[0]);
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // SUBORDER

    public LiveData<List<Suborder>> getSubordersFromSameOrder(int id){
        return mSuborderDao.getByOrderID(id);
    }

    public void insertSuborder(Suborder suborder){
        new InsertSuborderAsyncTask(mSuborderDao).execute(suborder);
    }

    public void insertSuborders(Suborder...suborders){
        new InsertSubordersAsyncTask(mSuborderDao).execute(suborders);
    }

    public void updateSuborder(Suborder suborder){
        new UpdateSuborderAsyncTask(mSuborderDao).execute(suborder);
    }

    public void deleteSuborder(Suborder suborder){
        new DeleteSuborderAsyncTask(mSuborderDao).execute(suborder);
    }

    private class InsertSuborderAsyncTask extends AsyncTask<Suborder,Void,Void>{

        private SuborderDao mDao;

        InsertSuborderAsyncTask (SuborderDao dao){
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Suborder... suborders) {
            mDao.insertSuborder(suborders[0]);
            return null;
        }
    }

    private class InsertSubordersAsyncTask extends AsyncTask<Suborder,Void,Void>{

        private SuborderDao mDao;

        InsertSubordersAsyncTask(SuborderDao dao){
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Suborder... suborders) {
            mDao.insertSuborders(suborders);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mApplication.getApplicationContext(),"Porudzbina je sacuvana !", Toast.LENGTH_LONG).show();
        }
    }

    private class UpdateSuborderAsyncTask extends AsyncTask<Suborder,Void,Void>{

        private SuborderDao mDao;

        UpdateSuborderAsyncTask(SuborderDao dao){
            mDao  = dao;
        }

        @Override
        protected Void doInBackground(Suborder... suborders) {
            mDao.updateSuborder(suborders[0]);
            return null;
        }
    }

    private class DeleteSuborderAsyncTask extends AsyncTask<Suborder,Void,Void> {

        private SuborderDao mDao;

        DeleteSuborderAsyncTask(SuborderDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Suborder... suborders) {
            mDao.deleteSuborder(suborders[0]);
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // ORDER

    public LiveData<List<Orders>> getOrdersByUserId(long id){
       return mOrderDao.findByUserId(id);
    }

    public LiveData<List<OrderWithUser>> getUdeliveredOrders() {
        return mUndeliveredOrders;
    }

    public long insertOrder(Orders orders){

       /* new InsertOrderAsyncTask(mOrderDao).execute(orders);
        return orderIDD;*/

       try {
           return (new InsertOrderAsyncTask(mOrderDao).execute(orders).get()).longValue();
       }
       catch (CancellationException e){
           return 0;
       }
       catch (ExecutionException e){
           return 0;
       }
       catch (InterruptedException e){
           return 0;
       }
    }

    public void updateOrder(Orders orders){
        new UpdateOrderAsyncTask(mOrderDao).execute(orders);
    }

    public void deleteOrder(Orders orders){
        new DeleteOrderAsyncTask(mOrderDao).execute(orders);
    }

    private class InsertOrderAsyncTask extends AsyncTask<Orders,Void, Long>{
        private OrderDao mDao;

        InsertOrderAsyncTask(OrderDao dao){
            mDao = dao;
        }

        @Override
        protected Long doInBackground(Orders... orders) {

            return mDao.insertOrder(orders[0]);
        }

      /*  @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }*/
    }

    private class UpdateOrderAsyncTask extends AsyncTask<Orders,Void,Void>{
        private OrderDao mDao;

        UpdateOrderAsyncTask(OrderDao dao){
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Orders... orders) {
            mDao.updateOrder(orders[0]);
            return null;
        }
    }

    private class DeleteOrderAsyncTask extends AsyncTask<Orders,Void,Void>{
        private OrderDao mDao;

        DeleteOrderAsyncTask(OrderDao dao){
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Orders... orders) {
            mDao.deleteOrder(orders[0]);
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // OrderWithUser

    public LiveData<List<OrderWithUser>> getUndeliveredOrdersWithUser(){
        return mUndeliveredOrders;
    }

    // ProductFromSuborder

    public LiveData<List<ProductFromSuborder>> getProductAndQuantity(long id){
        return mProductFromSuborderDao.getProductAndQuantity(id);
    }
}
