package pl.com.patryk.parcar.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController

import pl.com.patryk.parcar.databinding.FragmentReservationBinding
import pl.com.patryk.parcar.models.ReservationViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import pl.com.patryk.parcar.views.adapters.ReservationAdapter


class ReservationFragment : Fragment() {

    private val viewModel: ReservationViewModel by viewModels {
        InjectorUtils.provideReservationViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentReservationBinding.inflate(inflater, container, false)
        context ?: return binding.root


        val adapter = ReservationAdapter()
        binding.recycleViewReservation.adapter = adapter
        subscribeUi(adapter)

        binding.floatingActionButton.setOnClickListener {
            val destination = ReservationFragmentDirections.actionReservationFragmentToAddReservationFragment(-1)
            findNavController().navigate(destination)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: ReservationAdapter) {
        viewModel.reservations.observe(viewLifecycleOwner) {
            if (it != null)
                adapter.submitList(it)
        }
    }

}
