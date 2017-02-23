package layout;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.corp_01agmp.ctov10.ADAPTERS.ClienteAdapter;
import com.example.corp_01agmp.ctov10.BD.GuardarCliente;
import com.example.corp_01agmp.ctov10.BD.Socio;
import com.example.corp_01agmp.ctov10.Data.dClientes;
import com.example.corp_01agmp.ctov10.ParsersSqlite.ClienteParserSQLite;
import com.example.corp_01agmp.ctov10.R;
import com.example.corp_01agmp.ctov10.Recursos.Cursores;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DatosClienteFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    List<dClientes> lclientes;
    ClienteAdapter adapter;
    Mensajes msg;

    private OnFragmentInteractionListener mListener;

    public DatosClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_datos_cliente, container, false);
        inicializa();
        return v;
    }

    private void inicializa() {
        msg = new Mensajes();
        GuardarCliente g = new GuardarCliente(getActivity());

        boolean valida = g.comprobarGuardado();

        if(valida){
            Cursores cursores = new Cursores();
            lclientes = new ArrayList<>();
            recyclerView = (RecyclerView) v.findViewById(R.id.rvClientData);
            LinearLayoutManager lm = new LinearLayoutManager(getContext());

            lm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(lm);

            lclientes = new ClienteParserSQLite(getContext()).parserFeedClienteData(cursores.cargaclienteguardado(getContext()));
            adapter = new ClienteAdapter(lclientes,getContext());
            recyclerView.setAdapter(adapter);
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
