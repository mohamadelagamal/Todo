package todo.ui.setting

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.route.todo_c35_sat.database.MyDataBase
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.FragmentScoped
import todo.ui.R
import todo.ui.databinding.FragmentSettingBinding
import java.util.*
import javax.inject.Inject

@FragmentScoped
class Fragment_Setting  (): Fragment() {
    lateinit var viewDataBinding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
        //... root is the root element inflation
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       viewDataBinding.textChangeLanguage.setOnClickListener {
           MaterialAlertDialogBuilder(requireContext())
               .setMessage(getString(R.string.select_language))
               .setPositiveButton(getString(R.string.english))
               { dialog, which->


                   val languageToLoad = "en" // your language
                   val locale = Locale(languageToLoad)
                   Locale.setDefault(locale)
                   val config = Configuration()
                   config.locale = locale
                   requireContext().resources.updateConfiguration(config,requireActivity().resources.displayMetrics)
                   getActivity()?.finish();
                   startActivity(getActivity()?.intent)
               }.setNegativeButton(getString(R.string.arabic)){ dialog, which->

                   val languageToLoad = "ar" // your language
                   val locale = Locale(languageToLoad)
                   Locale.setDefault(locale)
                   val config = Configuration()
                   config.locale = locale
                   requireContext().resources.updateConfiguration(config,requireActivity().resources.displayMetrics)
                   getActivity()?.finish();
                   startActivity(getActivity()?.intent)
               }.show()
       }
    }

}