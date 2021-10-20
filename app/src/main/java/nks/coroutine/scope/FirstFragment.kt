package nks.coroutine.scope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {
    val scope= CoroutineScope(CoroutineName("MyScope"))//if ve do not define dispatcher it will take DefaultDispatcher
    private val scopeD= CoroutineScope(Dispatchers.IO + CoroutineName("Dispatcher Scope")) //Dispatcher are added with + not ,

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_first, container, false)

        val job = scope.launch { //launch returns a job object
            Log.d("Coroutines",this.coroutineContext.toString())
            launch {                 //nested scope
                Log.d("Coroutines",this.coroutineContext.toString())
            }
        }

        scopeD.launch {
            Log.d("Coroutines",this.coroutineContext.toString())
        }

        view.findViewById<TextView>(R.id.first_text).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return view;
    }

}