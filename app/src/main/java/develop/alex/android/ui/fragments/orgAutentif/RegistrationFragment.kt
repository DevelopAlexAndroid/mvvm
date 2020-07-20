package develop.alex.android.ui.fragments.orgAutentif

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import develop.alex.android.R
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_registration_org.*
import java.util.concurrent.TimeUnit


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_org, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fixColorText()

        synchronizeEditTextANdTextView() //- реализация

        //RX editText
       /* val s = rxEditText()
            .debounce(1000, TimeUnit.MILLISECONDS)
            //.filter only phone
            .subscribe {
                //if don't pressed regular  -> show fail
                //if all ok show button
                Log.d("mylog", it)
            }*/


    }

    private fun rxEditText(): PublishSubject<String> {
        val subject = PublishSubject.create<String>()
        ed_phone_with_background.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = ed_phone_with_background.text.toString()
                subject.onNext(text)
            }
        })
        return subject
    }

    private fun synchronizeEditTextANdTextView() {
        ed_phone_with_background.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val text = ed_phone_with_background.text.toString()
                val textLength = ed_phone_with_background.text.length

                if (text.endsWith("-")) {
                    updateHint()
                    return
                }

                if (textLength == 1 || textLength == 4) {
                    if (!text.contains("+")) {
                        setText(StringBuilder(text).insert(text.length - 1, "+7 (").toString())
                    } else {
                        setText("")
                    }
                } else if (textLength == 8) {
                    setText(StringBuilder(text).insert(text.length - 1, ") ").toString())
                } else if (textLength == 9) {
                    setText(StringBuilder(text).removeRange(7, 9).toString())
                } else if (textLength == 13) {
                    if (!text.contains("-")) {
                        setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                    }
                } else if (textLength == 16) {
                    if (text.contains("-")) {
                        setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                    }
                }
                updateHint()
            }

            private fun setText(text: String) {
                ed_phone_with_background.removeTextChangedListener(this)
                ed_phone_with_background.editableText.replace(
                    0,
                    ed_phone_with_background.text!!.length,
                    text
                )
                ed_phone_with_background.setSelection(text.length)
                ed_phone_with_background.addTextChangedListener(this)
            }

            private fun updateHint() {
                val newHint = resources
                    .getString(R.string.hint_background)
                    .replaceRange(
                        0,
                        ed_phone_with_background.text.length,
                        ed_phone_with_background.text
                    )
                ed_background_input.hint = newHint
            }
        })
    }

    private fun fixColorText() {
//покраска
        val length = resources.getString(R.string.data_fot_you).length
        val word: Spannable =
            SpannableString(resources.getString(R.string.data_fot_you) + " 896152592093")
        word.setSpan(
            ForegroundColorSpan(Color.BLUE),
            length,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = word
    }
}