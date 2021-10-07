package com.pk.hangup.loginService.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pk.hangup.R
import com.pk.hangup.databinding.FragmentSignUpBinding

class SignUp : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        binding.idLoadingProgress.visibility = View.INVISIBLE
        binding.signUpBtn.setOnClickListener(View.OnClickListener {
            if (binding.signupName.text.toString().isNotEmpty()&&binding.signupEmail.text.toString().isNotEmpty()
                &&binding.signupPassword.text.toString().isNotEmpty())
            {
                startProgressLoading()
                firebaseAuth.createUserWithEmailAndPassword(binding.signupEmail.text.toString(),
                    binding.signupPassword.text.toString()).addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            val currentUsr = firebaseAuth.currentUser
                            val user = UserCredential(currentUsr?.uid.toString(),binding.signupName.text.toString(),
                                currentUsr?.email.toString(),binding.signupPassword.text.toString())
                            firebaseDatabase.reference.child("Users").child(currentUsr?.uid.toString()).setValue(user).addOnCompleteListener{task ->
                                if (task.isSuccessful)
                                {
                                    Toast.makeText(context,"User created successful",Toast.LENGTH_SHORT).show()
                                    firebaseAuth.signOut()
                                    stopProgressLoading()
                                    findNavController().navigate(R.id.login)
                                }else
                                {
                                    Toast.makeText(context,"Unable to create account",Toast.LENGTH_SHORT).show()
                                    firebaseAuth.signOut()
                                    stopProgressLoading()
                                }
                            }
                        }else
                        {
                            Toast.makeText(context,"Something went wrong ${task.exception}",
                                Toast.LENGTH_SHORT).show()
                            stopProgressLoading()
                        }

                }
            }else
                Toast.makeText(context,"Empty field not allowed",Toast.LENGTH_SHORT).show()
        })
        binding.signUpToLogin.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.login)
        })
        return binding.root
    }
    private fun startProgressLoading()
    {
        binding.signUpBtn.visibility =  View.INVISIBLE
        binding.idLoadingProgress.visibility = View.VISIBLE
    }
    private fun stopProgressLoading()
    {
        binding.signUpBtn.visibility =  View.VISIBLE
        binding.idLoadingProgress.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("signUp Fragment","onDestroy called ")
    }

    override fun onStop() {
        super.onStop()
        Log.i("signUp Fragment","onStop called ")
        onDestroy()
    }
}