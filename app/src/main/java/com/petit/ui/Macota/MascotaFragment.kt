package com.petit.ui.Macota

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.petit.databinding.FragmentMascotaBinding
import com.petit.viewModel.MascotaViewModel

class MascotaFragment : Fragment() {

    private var _binding: FragmentMascotaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mascotaViewModel =
            ViewModelProvider(this).get(MascotaViewModel::class.java)

        _binding = FragmentMascotaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}