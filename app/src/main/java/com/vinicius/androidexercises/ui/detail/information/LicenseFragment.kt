package com.vinicius.androidexercises.ui.detail.information

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import br.com.arch.toolkit.delegate.viewProvider
import br.com.arch.toolkit.statemachine.ViewStateMachine
import br.com.arch.toolkit.statemachine.setup
import br.com.arch.toolkit.statemachine.state
import com.vinicius.androidexercises.R
import com.vinicius.androidexercises.data.ui.License
import com.vinicius.androidexercises.ui.detail.LICENSE_ARGS

class LicenseFragment : Fragment(R.layout.license_fragment) {

    private val name: AppCompatTextView by viewProvider(R.id.license_name)
    private val empty: AppCompatTextView by viewProvider(R.id.license_empty)
    private val viewStateMachine = ViewStateMachine()
    private val stateData = 0
    private val stateEmpty = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewStateMachine()
        arguments?.getParcelable<License>(LICENSE_ARGS)?.run {
            this@LicenseFragment.name.text = name
            viewStateMachine.changeState(stateData)
        } ?: run {
            viewStateMachine.changeState(stateEmpty)
        }
    }

    private fun setupViewStateMachine() {
        viewStateMachine.setup {
            state(stateData) {
                visibles(name)
                gones(empty)
            }

            state(stateEmpty) {
                visibles(empty)
                gones(name)
            }
        }
    }

    override fun onDestroyView() {
        viewStateMachine.shutdown()
        super.onDestroyView()
    }

}