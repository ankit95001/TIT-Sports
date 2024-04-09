package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class WhatsappGroup extends Fragment {


    public WhatsappGroup() {
        // Required empty public constructor
    }
    ImageView chess_call,chess_what,kabaddi_call,kabaddi_what,volley_call,volley_what,bad_call,bad_what,foot_call,foot_what,basket_call,basket_what,cricket_call,cricket_what;
    ImageView athe_call,athe_what,hand_call,hand_what,tt_call,tt_what,tae_call,tae_what,kho_call,kho_what,swi_call,swi_what,weight_call,weight_what,yoga_call,yoga_what;
    ImageView other_call,other_what;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_whatsapp_group, container, false);

        //Chess
        chess_call=v.findViewById(R.id.chess_call);
        chess_what=v.findViewById(R.id.chess_whatsapp);
        chess_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7870793511"));
            startActivity(icall);
        });
        chess_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/JRgzJ7km1sg6YOoSoJnDyG";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bad_call=v.findViewById(R.id.badminton_call);
        bad_what=v.findViewById(R.id.badminton_whatsapp);
        bad_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7870793511"));
            startActivity(icall);
        });
        bad_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/FlqxrFXk02LGe0GA9G5Ay5";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cricket_call=v.findViewById(R.id.cricket_call);
        cricket_what=v.findViewById(R.id.cricket_whatsapp);
        cricket_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9431888715"));
            startActivity(icall);
        });
        cricket_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/DypsfhawGmkJLRfYP7WOLP";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        foot_call=v.findViewById(R.id.football_call);
        foot_what=v.findViewById(R.id.football_whatsapp);
        foot_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9693794510"));
            startActivity(icall);
        });
        foot_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/B8fFvhBrrRhLC7pF6t6dsF";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        volley_call=v.findViewById(R.id.volleyball_call);
        volley_what=v.findViewById(R.id.volleyball_whatsapp);
        volley_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9990115961"));
            startActivity(icall);
        });
        volley_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/DPLLd4tyoywEpK1f9b1SqG";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        yoga_call=v.findViewById(R.id.yoga_call);
        yoga_what=v.findViewById(R.id.yoga_whatsapp);
        yoga_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7277153935"));
            startActivity(icall);
        });
        yoga_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/KIzBtU1Gz2X36bjmOAiqSo";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        kho_call=v.findViewById(R.id.khokho_call);
        kho_what=v.findViewById(R.id.khokho_whatsapp);
        kho_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9608545089"));
            startActivity(icall);
        });
        kho_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/CI8mYyUQ9sS9xATUfORsm4";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hand_call=v.findViewById(R.id.handball_call);
        hand_what=v.findViewById(R.id.handball_whatsapp);
        hand_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9990115961"));
            startActivity(icall);
        });
        hand_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/BtAv1tEmCtY3USieXWtUQR";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tt_call=v.findViewById(R.id.tt_call);
        tt_what=v.findViewById(R.id.tt_whatsapp);
        tt_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9990115961"));
            startActivity(icall);
        });
        tt_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/FiQ9r3Xq11R94obaz0bezD";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        athe_call=v.findViewById(R.id.athletics_call);
        athe_what=v.findViewById(R.id.athletics_whatsapp);
        athe_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7783013324"));
            startActivity(icall);
        });
        athe_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/KRNllOglto7Iizr3aQzDfE";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        kabaddi_call=v.findViewById(R.id.kabaddi_call);
        kabaddi_what=v.findViewById(R.id.kabaddi_whatsapp);
        kabaddi_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9696439931"));
            startActivity(icall);
        });
        kabaddi_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/CpsAhWvwUpC8ygwkAgNQRc";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        other_call=v.findViewById(R.id.others_call);
        other_what=v.findViewById(R.id.others_whatsapp);
        other_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7771003944"));
            startActivity(icall);
        });
        other_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/FvWpEFJcuCWLj32POqCV5m";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weight_call=v.findViewById(R.id.weight_call);
        weight_what=v.findViewById(R.id.weight_whatsapp);
        weight_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 6203095190"));
            startActivity(icall);
        });
        weight_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/GUlJvAgMk6L5FYSAFJGlor";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        swi_call=v.findViewById(R.id.swimming_call);
        swi_what=v.findViewById(R.id.swimming_whatsapp);
        swi_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7783013324"));
            startActivity(icall);
        });
        swi_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/F221eYRBbgJ6VTq3CyIeGH";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        basket_call=v.findViewById(R.id.basketball_call);
        basket_what=v.findViewById(R.id.basketball_whatsapp);
        basket_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 9905046375"));
            startActivity(icall);
        });
        basket_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/INOOcviMsyQ0G8ttpliwj7";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tae_call=v.findViewById(R.id.taekwondo_call);
        tae_what=v.findViewById(R.id.taekwondo_whatsapp);
        tae_call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7277153935"));
            startActivity(icall);
        });
        tae_what.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/ILyr6y2HacsGIQm8cbGdgg";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return v;
    }
}