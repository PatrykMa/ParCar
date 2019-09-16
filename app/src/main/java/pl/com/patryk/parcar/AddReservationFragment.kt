package pl.com.patryk.parcar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import pl.com.patryk.parcar.models.AddReservationViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils


class AddReservationFragment : Fragment() {


    private  val viewModel: AddReservationViewModel by viewModels {
        InjectorUtils.provideAddReservationViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_reservtion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
