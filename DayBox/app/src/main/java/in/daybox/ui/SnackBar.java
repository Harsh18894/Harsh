package in.daybox.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;

import in.daybox.R;
import in.daybox.dto.MessageCustomDialogDTO;

/**
 * Created by Dell on 2/13/2016.
 */
public class SnackBar {

    public static void show(Context context, MessageCustomDialogDTO messageCustomDialogDTO) {


        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf");

        com.nispok.snackbar.Snackbar snackbar = com.nispok.snackbar.Snackbar.with(context)
                .text(messageCustomDialogDTO.getMessage())
                .textColor(Color.parseColor("#ffffffff"))
                .color(Color.parseColor("#fff18080"))
                .textTypeface(tf)
                .type(SnackbarType.MULTI_LINE)
                .actionLabel(messageCustomDialogDTO.getButton())
                .actionColor(Color.parseColor("#ffffffff"))
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(com.nispok.snackbar.Snackbar snackbar) {

                    }
                });

        snackbar.setPadding(5, 5, 5, 5);

        SnackbarManager.show(snackbar.duration(com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT));
    }

    public static void success(Context context, MessageCustomDialogDTO messageCustomDialogDTO) {


        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf");

        com.nispok.snackbar.Snackbar snackbar =  com.nispok.snackbar.Snackbar.with(context)
                .text(messageCustomDialogDTO.getMessage())
                .textColor(Color.parseColor("#ffffffff"))
                .color(Color.parseColor("#ff0bc681"))
                .textTypeface(tf)
                .type(SnackbarType.MULTI_LINE)
                .actionLabel(messageCustomDialogDTO.getButton())
                .actionColor(Color.parseColor("#ffffffff"))
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(com.nispok.snackbar.Snackbar snackbar) {

                    }
                });

        snackbar.setPadding(5, 5, 5, 5);

        SnackbarManager.show(snackbar.duration(com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT));
    }

    public static void noInternet(Context context) {

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf");

        com.nispok.snackbar.Snackbar snackbar =  com.nispok.snackbar.Snackbar.with(context)
                .text(context.getResources().getString(R.string.gcm_activity_no_internet))
                .textColor(Color.parseColor("#ffffffff"))
                .color(Color.parseColor("#fff18080"))
                .textTypeface(tf)
                .type(SnackbarType.MULTI_LINE)
                .actionLabel(context.getResources().getString(R.string.ok))
                .actionColor(Color.parseColor("#ffffffff"))
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(com.nispok.snackbar.Snackbar snackbar) {

                    }
                });

        snackbar.setPadding(5, 5, 5, 5);

        SnackbarManager.show(snackbar.duration(com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT));
    }

}