package com.example.txilla.domometeo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.txilla.domometeo.Helpers.DataManager;
import com.example.txilla.domometeo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link UtilitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UtilitiesFragment extends Fragment {

    @BindView(R.id.serverAdressEditText) EditText serverAdress;
    @BindView(R.id.portEditText) EditText port;
    @BindView(R.id.pbConfig) ProgressBar pb;
    @BindView(R.id.saveButton) Button buttonSave;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Unbinder unbinder;

    //private OnFragmentInteractionListener mListener;

    public UtilitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment UtilitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UtilitiesFragment newInstance() {
        UtilitiesFragment fragment = new UtilitiesFragment();
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //
        View view = inflater.inflate(R.layout.fragment_utilities, container, false);
        unbinder = ButterKnife.bind(this, view);



        return view;
    }

    @OnClick(R.id.saveButton)
    public void save() {
        pb.setVisibility(View.VISIBLE);
        buttonSave.setEnabled(false);
        clickSave();
    }

    private void clickSave() {
        String serverAdressString = serverAdress.getText().toString();
        String portString = port.getText().toString();

        DataManager.saveSharedPreferences(serverAdressString, portString, getContext());

        Toast.makeText(getActivity(),"saved", Toast.LENGTH_LONG).show();
        pb.setVisibility(View.INVISIBLE);
        buttonSave.setEnabled(true);

    }


    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
    }*/

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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}