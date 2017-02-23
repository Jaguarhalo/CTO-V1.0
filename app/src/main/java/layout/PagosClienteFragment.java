package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.corp_01agmp.ctov10.ADAPTERS.PagosAdapter;
import com.example.corp_01agmp.ctov10.BD.GuardarCliente;
import com.example.corp_01agmp.ctov10.Data.dPagos;
import com.example.corp_01agmp.ctov10.ParsersSqlite.PagosParserSQLite;
import com.example.corp_01agmp.ctov10.R;
import com.example.corp_01agmp.ctov10.Recursos.Cursores;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PagosClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PagosClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagosClienteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    View v;
    RecyclerView recyclerview;
    List<dPagos> lpagos;
    PagosAdapter adapter;
    Mensajes msg;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PagosClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagosClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagosClienteFragment newInstance(String param1, String param2) {
        PagosClienteFragment fragment = new PagosClienteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pagos_cliente, container, false);
        inicializa();
        return v;
    }

    private void inicializa() {
        GuardarCliente g = new GuardarCliente(getActivity());

        boolean valida = g.comprobarGuardado();

        if(valida){
            Cursores cursores = new Cursores();
            String nombre;
            lpagos = new ArrayList<>();
            recyclerview = (RecyclerView) v.findViewById(R.id.rvPagos);
            LinearLayoutManager lm = new LinearLayoutManager(getContext());

            lm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setHasFixedSize(true);
            recyclerview.setLayoutManager(lm);

            nombre = cursores.cargaclienteguardado(getContext());
            lpagos = new PagosParserSQLite(getActivity()).parserFeedPagosData(cursores.cargaidclienteguardado(getActivity(),nombre));
            adapter = new PagosAdapter(lpagos,getContext());
            recyclerview.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(),"Favor de realizar la busqueda de cliente.",Toast.LENGTH_SHORT).show();
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
}
