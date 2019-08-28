package pl.com.patryk.parcar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class PaymentMethodFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentMethodFragment()
    }

    private lateinit var viewModel: PaymentMethodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payment_method_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PaymentMethodViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
