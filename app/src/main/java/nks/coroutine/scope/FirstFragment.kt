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
    private val scope= CoroutineScope(CoroutineName("MyScope"))//if ve do not define dispatcher it will take DefaultDispatcher

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_first, container, false)

        val mainJob=scope.launch { //launch returns a job object
            val job1=launch {
                while(isActive){
                    Log.d("Coroutines","Job 1 Running...")
                }
            }
            val job2=launch {
                while (true){
                    ensureActive()
                    Log.d("Coroutines","Job 2 Running...")
                }
            }
            val job3=launch {
                while (true){
                    delay(50L)
                    Log.d("Coroutines","Job 2 Running...")
                }
            }
            val job4=launch {
                while (true){
                    yield()
                    Log.d("Coroutines","Job 2 Running...")
                }
            }
            delay(1000L)
            Log.d("Coroutines","Canceling..")
            job1.cancel()
            job1.join() //will wait till the job is completed and only the log the completed message
            job3.cancelAndJoin()
            Log.d("CoroutinesC","Job 1 Cancelled..")
        }

        runBlocking {
            delay(2000L)
            Log.d("Coroutines","Canceling..")
            mainJob.cancelAndJoin()
            Log.d("CoroutinesC","MainJob Cancelled..")

        }

        view.findViewById<TextView>(R.id.first_text).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return view;
    }

}