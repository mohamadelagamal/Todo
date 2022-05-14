package todo.ui.fragment.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.w3c.dom.Text
import todo.ui.R
import todo.ui.databinding.FragmentSettingBinding

class Fragment_Setting: Fragment() {
    lateinit var viewDataBinding:FragmentSettingBinding
    lateinit var viewModel: FragmentViewModel
    lateinit var spinner: Spinner
    lateinit var spinnerMode:Spinner
    lateinit var result:TextView
    val types = arrayOf("Arabic", "English")
    val mode = arrayOf("Light", "Dark")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
       spinner= viewDataBinding.spinner
        spinnerMode = viewDataBinding.spinnerMode
        spinner?.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item
            , types) as SpinnerAdapter
        spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
               // Toast.makeText(activity,type, Toast.LENGTH_LONG).show()
                println(type)
            }

        }
        spinnerModeInitView()
        return viewDataBinding.root
    }

    private fun spinnerModeInitView() {
        spinnerMode?.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item
            , mode) as SpinnerAdapter
        spinnerMode?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                // Toast.makeText(activity,type, Toast.LENGTH_LONG).show()
                if (type=="Light"){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                 if (type=="Dark"){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


                }
            }

        }
    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//            spinner = requireActivity().findViewById(R.id.spinner) as Spinner
//            result = requireActivity().findViewById(R.id.textResult) as TextView
//        val options = arrayOf("Arabic","English")
//        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
//            spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                result.text = options.get(p2)}
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//
//                }
//            }
//
//
//    }
}