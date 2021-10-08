package com.pk.hangup.mainUI.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.pk.hangup.LoginServiceActivity
import com.pk.hangup.R
import com.pk.hangup.databinding.FragmentUserProfileBinding

class UserProfile : Fragment(),PopupMenu.OnMenuItemClickListener {
    private lateinit var binding:FragmentUserProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.proFileAppBar.idMenuIcon.setOnClickListener(View.OnClickListener {
            showMenu(it)
        })
        return binding.root
    }
    private fun showMenu(v:View)
    {
        PopupMenu(context,v).apply {
            setOnMenuItemClickListener(this@UserProfile)
            inflate(R.menu.profile_menu)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when(item?.itemId)
        {
            R.id.idSettings->{true}
            R.id.idLogOut->{
                firebaseAuth.signOut()
                val intent = Intent(activity,LoginServiceActivity::class.java)
                startActivity(intent)
                activity?.finish()
                true
            }
            else -> false
        }
    }
}