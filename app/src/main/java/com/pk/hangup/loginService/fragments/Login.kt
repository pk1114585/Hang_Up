package com.pk.hangup.loginService.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.pk.hangup.MainActivity
import com.pk.hangup.R
import com.pk.hangup.databinding.FragmentLoginBinding

class Login : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.idLoadingProgress.visibility = View.INVISIBLE
        binding.idLoginBtn.setOnClickListener(View.OnClickListener {
            if (binding.idLoginEmail.text.toString().isNotEmpty()
                &&binding.idLoginPassword.text.toString().isNotEmpty())
            {
                startProgressLoading()
                firebaseAuth.signInWithEmailAndPassword(binding.idLoginEmail.text.toString()
                        ,binding.idLoginPassword.text.toString()).addOnCompleteListener {
                            task->
                    if (task.isSuccessful)
                    {
                        val intent = Intent(activity,MainActivity::class.java)
                        startActivity(intent)
                        stopProgressLoading()
                        activity?.finish()
                    }else
                    {
                        Toast.makeText(context,"Login error ${task.exception}",Toast.LENGTH_SHORT).show()
                        stopProgressLoading()
                    }
                }
            }
        })
        binding.loginToSignUp.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.signUp)
        })
        return binding.root
    }
    private fun startProgressLoading()
    {
        binding.idLoginBtn.visibility =  View.INVISIBLE
        binding.idLoadingProgress.visibility = View.VISIBLE
    }
    private fun stopProgressLoading()
    {
        binding.idLoginBtn.visibility =  View.VISIBLE
        binding.idLoadingProgress.visibility = View.INVISIBLE
    }
}