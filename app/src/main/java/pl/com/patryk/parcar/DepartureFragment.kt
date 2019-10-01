package pl.com.patryk.parcar

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.departure_fragment.*
import pl.com.patryk.parcar.databinding.DepartureFragmentBinding
import pl.com.patryk.parcar.models.DepartureViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import pl.com.patryk.parcar.views.NamedFragment
import pl.com.patryk.parcar.views.adapters.DepartureAdapter


class DepartureFragment : NamedFragment() {


    private val viewModel: DepartureViewModel by viewModels {
        InjectorUtils.provideDepartureViewModelFactory(requireContext())
    }
    lateinit var binding : DepartureFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DepartureFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root


        val adapter = DepartureAdapter()
        binding.recycleDeparture.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
        addButton.setOnClickListener {
           findNavController().navigate(R.id.action_departureFragment_to_addDepartureFragment)
    }
    }


    private fun subscribeUi(adapter: DepartureAdapter) {
        viewModel.departures.observe(viewLifecycleOwner) { departures ->
            /**
             *  Plant may return null, but the [observe] extension function assumes it will not be null.
             *  So there will be a warning（Condition `departures != null` is always `true`） here.
             *  I am not sure if the database return data type should be defined as nullable, Such as `LiveData<List<Plant>?>` .
             */
            if (departures != null) {
                binding.textViewEmptyRecycler.visibility = if(departures.isNotEmpty()) View.GONE else View.VISIBLE
                adapter.submitList(departures)
            }
        }
    }
}
