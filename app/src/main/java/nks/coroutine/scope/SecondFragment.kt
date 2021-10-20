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


class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_second, container, false)


        CoroutineScope(Dispatchers.IO).launch {
            printFollower()
        }

        view.findViewById<TextView>(R.id.second_text).setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
        return view;
    }

    private suspend fun printFollower() {
        CoroutineScope(Dispatchers.IO).launch {
            val fb=async { getFb() } //both fun call will run parallel while using launch only without async it will run one after another
            val insta=async { getInsta() }
            Log.d("TAG","FB - ${fb.await()}, Insta - ${insta.await()}")
        }
    }

    private suspend fun getFb():Int{
        delay(1000L)
        return 113
    }

    private suspend fun getInsta():Int{
        delay(1000L)
        return 91
    }

}