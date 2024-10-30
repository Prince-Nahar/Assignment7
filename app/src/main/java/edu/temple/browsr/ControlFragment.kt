package edu.temple.browsr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ControlFragment : Fragment() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var backButton: ImageView
    private var listener: ControlFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_control, container, false)

        urlEditText = view.findViewById(R.id.urlEditText)
        goButton = view.findViewById(R.id.goButton)
        nextButton = view.findViewById(R.id.nextButton)
        backButton = view.findViewById(R.id.backButton)

        goButton.setOnClickListener {
            val url = urlEditText.text.toString()
            listener?.onGoClicked(url)
        }

        nextButton.setOnClickListener {
            listener?.onNextClicked()
        }

        backButton.setOnClickListener {
            listener?.onBackClicked()
        }

        return view
    }

    fun setControlFragmentListener(listener: ControlFragmentListener) {
        this.listener = listener
    }

    interface ControlFragmentListener {
        fun onGoClicked(url: String)
        fun onNextClicked()
        fun onBackClicked()
    }
}
