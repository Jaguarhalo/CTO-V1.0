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

import com.example.corp_01agmp.ctov10.ADAPTERS.CreditosAdapter;
import com.example.corp_01agmp.ctov10.BD.GuardarCliente;
import com.example.corp_01agmp.ctov10.Data.dClientes;

import com.example.corp_01agmp.ctov10.Data.dCreditos;
import com.example.corp_01agmp.ctov10.ParsersSqlite.CreditosParserSQLite;
import com.example.corp_01agmp.ctov10.R;
import com.example.corp_01agmp.ctov10.Recursos.Cursores;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreditosClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreditosClienteFragment extends Fragment {
    View v;
    RecyclerView recyclerview;
    List<dCreditos> lcreditos;
    CreditosAdapter adapter;
    Mensajes msg;


    private OnFragmentInteractionListener mListener;

    public CreditosClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_creditoscliente, container, false);
        inicializa();
        return v;
    }

    private void inicializa() {
        GuardarCliente g = new GuardarCliente(getActivity());

        boolean valida = g.comprobarGuardado();

        if(valida){
            Cursores cursores = new Cursores();
            String nombre;
            lcreditos = new ArrayList<>();
            recyclerview = (RecyclerView) v.findViewById(R.id.rvCreditos);
            LinearLayoutManager lm = new LinearLayoutManager(getContext());

            lm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setHasFixedSize(true);
            recyclerview.setLayoutManager(lm);

            nombre = cursores.cargaclienteguardado(getContext());
            msg = new Mensajes().mostrarMensaje(v,getContext(),nombre);
            lcreditos = new CreditosParserSQLite(getActivity()).parserFeedCreditoData(cursores.cargaidclienteguardado(getActivity(),nombre));
            adapter = new CreditosAdapter(lcreditos, getActivity());
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
