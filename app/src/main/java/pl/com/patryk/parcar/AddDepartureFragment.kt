package pl.com.patryk.parcar

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_departure.*
import pl.com.patryk.parcar.models.AddDepartureViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import pl.com.patryk.parcar.views.DepartureOptionsDialogArgs

class AddDepartureFragment : Fragment() {

    private val args: AddDepartureFragmentArgs by navArgs()
    private val viewModel: AddDepartureViewModel by viewModels {
        InjectorUtils.provideAddDepartureViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setDeparture(args.departureId)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_departure, container, false)

    }

    override fun onResume() {
        super.onResume()
        pager.adapter = AddDepartureFragmentStatepageAdapter(fragmentManager!!)
        setOnAddListener()
    }

    fun setOnAddListener()
    {
        fab_addDeparture.setOnClickListener {

            viewModel.addDeparture()
            findNavController().navigate(R.id.action_addDepartureFragment_to_departureFragment)

        }

    }



    enum class FragmentKind(val pos:Int){
        Time(0),
        Payment(1),
        Information(2);

        val fragment:Fragment
            get() {return when(this)
            {
                Time -> AddDepartureTimeFragment()
                Payment -> AddDeparturePaymentFragment()
                Information -> AddDepartureInformationFragment()
            }}
        companion object{
            operator fun get(pos: Int):FragmentKind
            {
                values().forEach {
                    if (it.pos == pos)
                        return it
                }
                throw Exception("unknown position")
            }

            val count:Int = values().count()
        }
    }

    inner class AddDepartureFragmentStatepageAdapter(manager:FragmentManager) : FragmentStatePagerAdapter(manager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
        override fun getItem(position: Int): Fragment {
           return FragmentKind[position].fragment
        }

        override fun getCount(): Int {
           return FragmentKind.count
        }
    }
}
