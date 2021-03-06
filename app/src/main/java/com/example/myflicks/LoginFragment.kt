package com.example.myflicks

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //istancja viewmodelu
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )).get(MainViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reakcja na przycisk "Log in"
        buttonLogin.setOnClickListener { view -> kotlin.run {
            var email = editTextLoginEmail.text.toString().trim()
            var password = editTextLoginPassword.text.toString()
            //kontrola czy pola zosta??y wype??nione
            if (email.isEmpty())
            {
                editTextLoginEmail.error = "E-mail is required."
            }
            else
            {
                if(password.isEmpty())
                {
                    editTextLoginPassword.error = "Password is required."
                }
                else
                {
                    //zalogowanie si?? z wykorzystaniem firebase i instancji fAuth zawartej w viewmodelu
                    mainViewModel.fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

                        } else {
                            //gdy firebase zwr??ci b????d, wy??wietla si?? on r??wnie?? na ekranie
                            Toast.makeText(context,"Error. " + (task.exception?.message ?: ""),Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                }
            }

        }

        //po kliknieciu w przycisk "sign up" aplikacja nawiguje do odpowiedniego widoku
        buttonLoginToRegister.setOnClickListener { view -> view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}