package com.ventpar.skurdnja;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ventpar.skurdnja.Adapters.UnsentOrdersAdapter;
import com.ventpar.skurdnja.Adapters.UnsetSubordersAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUser;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.ProductFromSuborder;
import com.ventpar.skurdnja.SkurdnjaViewModels.OrderViewModel;
import com.ventpar.skurdnja.SkurdnjaViewModels.UnsentProductsViewModel;

import java.util.Calendar;
import java.util.List;

public class UnsentOrderDetailsActivity extends AppCompatActivity {


    private TextView mUserName;
    private TextView mAddress;
    private TextView mCity;
    private TextView mTelephoneNumber;
    private TextView mTotalPrice;
    private RecyclerView mRecyclerView;
    private CardView mCardView;
    private Button mOrderSentBtn;
    private OrderWithUser mOrderWithUser;
    private UnsentProductsViewModel mViewModel;
    private UnsetSubordersAdapter mAdapter;
    private Double mTotalOrderPrice = 0.0;
    private String mBuyerEmail;
    private OrderViewModel mOrderViewModel;
    private Button mCallButton;
    private Button mSmsButton;
    private Button mEmailButton;
    private Button mViberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsent_order_details);

        final Intent intent = getIntent();

        mOrderWithUser = (OrderWithUser) intent.getSerializableExtra("ORDER");

        mViewModel = ViewModelProviders.of(this).get(UnsentProductsViewModel.class);
        mOrderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);


        mUserName = findViewById(R.id.unsent_details_name);
        mAddress = findViewById(R.id.unsent_details_address);
        mCity = findViewById(R.id.unsent_details_city);
        mTelephoneNumber = findViewById(R.id.unsent_details_phone_number);
        mTotalPrice = findViewById(R.id.unsent_details_total_price);

        mOrderSentBtn = findViewById(R.id.unsent_details_send_button);
        //TODO ide kod koji menja u orderu unsent u sent , ubacuje datum i ta sranja
        // plus dialog koji pita korisnika da li je zaistinski poslao skurdnju

        mOrderSentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*Uri gmUri = Uri.parse("geo:0,0?q=Ivana Gorana Kovacica 53,Paracin,Serbia");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if(mapIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Google Maps nije instaliran !!!", Toast.LENGTH_LONG).show();
                }*/

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UnsentOrderDetailsActivity.this)
                        .setTitle("Potvrdite slanje !")
                        .setMessage("Potvrdite da je posiljka poslana !")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Orders tmp = mOrderWithUser.order;
                                tmp.setDelivered(1);
                                Calendar.getInstance().getTime();
                                mOrderViewModel.updateOrder(tmp);
                                finish();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();

        }});

       // DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);

        mRecyclerView = findViewById(R.id.unsent_details_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        //mRecyclerView.addItemDecoration(itemDecoration);
        //TODO adapter i ostalo
        mViewModel.getProductAndQuantity(mOrderWithUser.order.getId()).observe(this, new Observer<List<ProductFromSuborder>>() {
            @Override
            public void onChanged(List<ProductFromSuborder> productFromSuborders) {
                mAdapter = new UnsetSubordersAdapter(getApplicationContext(), productFromSuborders);
                mRecyclerView.setAdapter(mAdapter);

                for(ProductFromSuborder p : productFromSuborders){
                    mTotalOrderPrice += p.product.getPrice() * p.suborder.getQuantity();
                }

                mTotalPrice.setText("Ukupno : " + Double.toString(mTotalOrderPrice) + " dinara.");
            }
        });


        mBuyerEmail = mOrderWithUser.user.getEmail();

        mUserName.setText(mOrderWithUser.user.getUserName() + " "
            + mOrderWithUser.user.getUserSurname());

        mCity.setText(mOrderWithUser.user.getCity());
        mTelephoneNumber.setText(mOrderWithUser.user.getTelephoneNumber());
        mAddress.setText(mOrderWithUser.user.getAddress());

        mCardView = findViewById(R.id.unsent_details_card_view);
       /* mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(UnsentOrderDetailsActivity.this, mCardView);

                popupMenu.getMenuInflater().inflate(R.menu.card_view_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.call_buyer :
                                String telN = "tel:" + mTelephoneNumber.getText();
                                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                intent1.setData(Uri.parse(telN));
                                startActivity(intent1);
                                break;

                            case R.id.message_buyer :
                                String msgN = "smsto:" + mTelephoneNumber.getText();
                                Intent intent2 = new Intent(Intent.ACTION_SENDTO, Uri.parse(msgN));
                                startActivity(intent2);
                                break;

                            case R.id.call_buyer_viber:
                                String telN1 = "tel:" + mTelephoneNumber.getText();
                                Intent viberIntent = new Intent("android.intent.action.VIEW");
                                if(viberIntent.resolveActivity(getPackageManager()) != null){
                                    viberIntent.setClassName("com.viber.voip","com.viber.voip.WelcomeActivity");
                                    viberIntent.setData(Uri.parse(telN1));
                                    startActivity(viberIntent);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Viber nije instaliran !", Toast.LENGTH_LONG).show();
                                }

                                break;

                            case R.id.email_buyer:

                                if(mBuyerEmail.isEmpty()){
                                    Toast.makeText(getApplicationContext(),"Greska: Niste uneli e-mail kupca !", Toast.LENGTH_LONG).show();
                                }
                                else
                                    {
                                        Intent emailBuyer = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mBuyerEmail));
                                        startActivity(emailBuyer);
                                }
                                break;
                                default:
                                    break;
                        }
                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });*/

        mCallButton = findViewById(R.id.call_button);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telN = "tel:" + mTelephoneNumber.getText();
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse(telN));
                startActivity(intent1);
            }
        });

        mSmsButton = findViewById(R.id.sms_button);
        mSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgN = "smsto:" + mTelephoneNumber.getText();
                Intent intent2 = new Intent(Intent.ACTION_SENDTO, Uri.parse(msgN));
                startActivity(intent2);
            }
        });

        mEmailButton = findViewById(R.id.email_button);
        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBuyerEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Greska: Niste uneli e-mail kupca !", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent emailBuyer = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mBuyerEmail));
                    startActivity(emailBuyer);
                }
            }
        });

        mViberButton = findViewById(R.id.viber_button);
        mViberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String telN1 = "tel:" + mTelephoneNumber.getText();
                    Intent viberIntent = new Intent("android.intent.action.VIEW");
                    viberIntent.setClassName("com.viber.voip","com.viber.voip.WelcomeActivity");
                    viberIntent.setData(Uri.parse(telN1));
                    startActivity(viberIntent);
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Viber nije instaliran !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
