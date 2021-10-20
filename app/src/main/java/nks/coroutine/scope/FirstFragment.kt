package nks.coroutine.scope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*


class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_first, container, false)

        GlobalScope.launch { //GlobalScope Does not destroy with fragment so lasts till app is running leading to data leak
            while (true){
                delay(1000L)
                Log.d("Coroutines","Running...")
            }
        }

        view.findViewById<TextView>(R.id.first_text).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return view;
    }

    override fun onPause() {
        Log.d("Coroutines","onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d("Coroutines","onResume")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d("Coroutines","onDestroy")
        super.onDestroy()
    }

    override fun onStop() {
        Log.d("Coroutines","onStop")
        super.onStop()
    }
}