package com.teacherworkout.commons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {
    lateinit var viewBinding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    fun requireCompatActivity(): AppCompatActivity {
        requireActivity()
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            return activity
        } else {
            throw TypeCastException("Main activity should extend from 'AppCompatActivity'")
        }
    }
}
