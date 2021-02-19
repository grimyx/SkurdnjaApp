package com.ventpar.skurdnja;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserProductsChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserProductsChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProductsChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private PieChart mPieChart;

    public UserProductsChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProductsChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProductsChartFragment newInstance(String param1, String param2) {
        UserProductsChartFragment fragment = new UserProductsChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_products_chart, container, false);
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPieChart = view.findViewById(R.id.chart_buyerProducts);

        // kreiram praznu listu
        List<PieEntry> entries = new ArrayList<>();

        // dodajem postotke dijagrama u listu
        entries.add(new PieEntry(18.5f, "Red"));
        entries.add(new PieEntry(26.7F, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        // kreiram data set
        PieDataSet dataSet = new PieDataSet(entries, "Skurdnja");
        // dataSet.setColors(R.color.colorAccent,R.color.colorPrimary);
        // od data seta kreiram podatke za dijagram

        final int[] MY_COLORS = {Color.rgb(192,0,0), Color.rgb(255,0,0), Color.rgb(255,192,0),
                Color.rgb(127,127,127), Color.rgb(146,208,80), Color.rgb(0,176,80), Color.rgb(79,129,189)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        mPieChart.setData(data);

        // refresuje dijagram
        mPieChart.invalidate();
    }
}
