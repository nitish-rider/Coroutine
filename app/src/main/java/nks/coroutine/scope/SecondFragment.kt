package nks.coroutine.scope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_second, container, false)
        view.findViewById<TextView>(R.id.second_text).setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
        return view;
    }

}