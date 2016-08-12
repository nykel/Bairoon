package br.com.bairoon.model.negocio.navigationdrawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.octicons_typeface_library.Octicons;

import br.com.bairoon.R;
import br.com.bairoon.model.bean.Usuario;
import br.com.bairoon.model.negocio.ActionsBairoon;
import br.com.bairoon.model.negocio.fonts.FontDosis;
import br.com.bairoon.model.negocio.fonts.ModificadorFonte;


/**
 * Created by 808627 on 10/08/2016.
 */
public class InstaladorNavigationDrawerBairoon {
    private AccountHeader accountHeader;
    private Drawer drawer;
    private IProfile profile;
    private Usuario usuario;
    private Activity activity;
    private AppCompatActivity appCompatActivity;
    private Bundle savedInstanceState;

    public InstaladorNavigationDrawerBairoon(Usuario usuario, AppCompatActivity appCompatActivity, Bundle savedInstanceState) {
        this.usuario = usuario;
        this.activity = (Activity) appCompatActivity;
        this.appCompatActivity = appCompatActivity;
        this.profile = new ProfileDrawerItem().withName(usuario.getNome()).withIcon(R.drawable.medic)/*.withEmail("nykelandersow@gmail.com")*/;

    }

    public AccountHeader configurarHeaderAccount() {
        accountHeader = new AccountHeaderBuilder()
                .withTextColor(ContextCompat.getColor(activity, R.color.colorWhite))
                .withActivity(activity)
                .withCompactStyle(false)
                        // .withHeightPx(UIUtils.getActionBarHeight(activity))
                        //.withHeaderBackground(new ColorDrawable(ContextCompat.getColor(activity, R.color.colorPrimary)))
                .withHeaderBackground(R.drawable.header4)
                        //.withHeightDp(80)
                .withDividerBelowHeader(true)
                .withSavedInstance(savedInstanceState)
                .withSelectionListEnabledForSingleProfile(false)
                .withProfileImagesVisible(false)
                .withPaddingBelowHeader(true)
                .withCurrentProfileHiddenInList(false)
                        // .addProfiles(profile)
                .withOnlyMainProfileImageVisible(false)
                .withAlternativeProfileHeaderSwitching(true)
                .withSelectionFirstLine(profile.getName().toString())
                        //.withSelectionSecondLine("Bem-vindo ao nosso portal de busca local")
                .withProfileImagesClickable(true)
                .withTranslucentStatusBar(true)
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile iProfile, boolean b) {
                        Intent activityPerfil = new Intent("PERFIL");

                        Bundle parametrosEnviar = new Bundle();
                        parametrosEnviar.putParcelable(Usuario.class.getName(), usuario);

                        activityPerfil.putExtras(parametrosEnviar);

                        activity.startActivity(activityPerfil);

                        return true;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile iProfile, boolean b) {
                        return false;
                    }
                })
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        Toast.makeText(activity, profile.getEmail().getText().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(activity, profile.getName().getText().toString(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                })
                .build();

        return accountHeader;
    }

    public Drawer configurarDrawer() {

        Typeface typeFaceDosisBold = ModificadorFonte.getInstance().configurarFont(FontDosis.DOSIS_REGULAR, activity);

        configurarHeaderAccount();

        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(accountHeader) //set the AccountHeader we created earlier for the header
                        //.withDrawerGravity(Gravity.END)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.lbl_home)
                                .withDescription(R.string.lbl_home_desc)
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withIdentifier(ActionsBairoon.HOME.getIdentificador())
                                .withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark))
                                .withIcon(Octicons.Icon.oct_home),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withName(R.string.lbl_pesquisar)
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withIdentifier(ActionsBairoon.PESQUISAR.getIdentificador())
                                .withDescription(R.string.lbl_pesquisar_desc)
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark))
                                .withIcon(Octicons.Icon.oct_search)
                                .withSelectable(false),
/*                        new PrimaryDrawerItem().withName(R.string.lbl_eventos)
                                .withDescription(R.string.lbl_eventos_desc)
                                .withSelectedTextColor(ContextCompat.getColor(activity, R.color.material_drawer_selected_text))
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withIdentifier(ActionsBairoon.EVENTOS.getIdentificador())
                                .withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark))
                                .withIcon(Octicons.Icon.oct_calendar),*/
                        new PrimaryDrawerItem()
                                .withName(R.string.lbl_como_anunciar)
                                .withDescription(R.string.lbl_como_anunciar_desc)
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withIdentifier(ActionsBairoon.COMO_ANUNCIAR.getIdentificador())
                                .withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark))
                                .withIcon(Octicons.Icon.oct_megaphone),
                        new PrimaryDrawerItem()
                                .withName(R.string.lbl_mensagens)
                                .withDescription(R.string.lbl_mensagens_desc)
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withIdentifier(ActionsBairoon.MENSAGENS.getIdentificador())
                                .withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark))
                                .withIcon(Octicons.Icon.oct_bell)
                                .withSelectable(false)
                                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.colorSecondaryDark)),
                        new PrimaryDrawerItem().withName(R.string.lbl_sair)
                                .withSelectedTextColor(ContextCompat.getColor(activity, R.color.material_drawer_selected_text))
                                .withIdentifier(ActionsBairoon.SAIR.getIdentificador())
                                .withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark))
                                .withIcon(Octicons.Icon.oct_log_out),
                        new DividerDrawerItem(),
                        //new PrimaryDrawerItem().withName("Área do Anunciante").withTextColor(ContextCompat.getColor(activity, R.color.material_drawer_divider)),
                        //new PrimaryDrawerItem().withName(R.string.lbl_contatos_uteis).withIdentifier(ActionsBairoon.CONTATO.getIdentificador()).withTypeface(typeFaceDosisBold).withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark)).withIcon(GoogleMaterial.Icon.gmd_account_box_mail),
                        new PrimaryDrawerItem()
                                .withName(R.string.lbl_configuracoes)
                                .withIcon(Octicons.Icon.oct_tools)
                                .withIdentifier(ActionsBairoon.CONFIGURACOES.getIdentificador())
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark)),
                        new SwitchDrawerItem()
                                .withName(R.string.lbl_notificacoes)
                                .withIdentifier(ActionsBairoon.NOTIFICACOES.getIdentificador())
                                .withIcon(GoogleMaterial.Icon.gmd_vibration)
                                .withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark)),
                        //  new ToggleDrawerItem().withName("Chat").withIcon(Octicons.Icon.oct_comment_discussion).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener).withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark)),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withName(R.string.lbl_sobre_o_app)
                                .withDescription(R.string.lbl_sobre_o_app_desc)
                                .withDescriptionTextColor(ContextCompat.getColor(activity, R.color.material_drawer_hint_text))
                                .withIdentifier(ActionsBairoon.SOBRE_O_APP.getIdentificador()).withTypeface(typeFaceDosisBold)
                                .withTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark)))
                .withSliderBackgroundColor(ContextCompat.getColor(activity, R.color.material_drawer_primary_dark))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        ActionsBairoon actionsBairoon = ActionsBairoon.recuperarAction(drawerItem.getIdentifier());

                        if ((actionsBairoon == null) || (!actionsBairoon.isImplementado())) {

                            LayoutInflater inflaterNaoImplementadoDialog = LayoutInflater.from(activity);

                            View dialogView = inflaterNaoImplementadoDialog.inflate(R.layout.dialog_nao_implementado, null);

                            AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(activity);

                            dialogbuilder.setView(dialogView);

                            final AlertDialog dialogDetails = dialogbuilder.create();

                            Button btnConcluir = (Button) dialogView.findViewById(R.id.btn_concluir);

                            btnConcluir.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogDetails.dismiss();
                                }
                            });

                            dialogDetails.show();

                            return true;
                        }

                        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Deseja sair do sistema?")
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        System.exit(0);
                                    }
                                })
                                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();

                                        if (Boolean.FALSE.equals(drawer.isDrawerOpen()))
                                            drawer.openDrawer();

                                    }
                                });
                        builder.create();

                        if (actionsBairoon == ActionsBairoon.HOME) {
                            Intent intent = new Intent(ActionsBairoon.HOME.toString());
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                            activity.startActivity(intent);
                            activity.finish();

                            return true;
                        }

                        if (actionsBairoon == ActionsBairoon.SAIR) {

                            if (drawer.isDrawerOpen())
                                drawer.closeDrawer();

                            builder.show();

                            return true;
                        }


                        Intent activitySlideShow = new Intent(actionsBairoon.toString());

                        Bundle parametrosEnviar = new Bundle();
                        parametrosEnviar.putParcelable(Usuario.class.getName(), usuario);

                        activitySlideShow.putExtras(parametrosEnviar);

                        activity.startActivity(activitySlideShow);

                        return true;
                    }
                })
                .withInnerShadow(true)
                .withSelectedItemByPosition(4)
                .withFullscreen(true)
                .withDisplayBelowStatusBar(true)
                .withHeaderPadding(false)
                .withGenerateMiniDrawer(false)
                .withSavedInstance(savedInstanceState)
/*
                                .withToolbar(restoreActionBar())
*/
                .withSliderBackgroundColor(ContextCompat.getColor(activity, R.color.colorWhite))
                .withSelectedItem(-1)
                .build();

        drawer.updateBadge(5, new StringHolder(String.valueOf("10")));
        //set the back arrow in the toolbar

        return drawer;

    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

/*    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(UIUtils.getThemeColorFromAttrOrRes(activity, R.attr.colorPrimaryDark, R.color.material_drawer_primary_dark));
            }

            mode.getMenuInflater().inflate(R.menu.cab, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }*/

    private Toolbar restoreActionBar() {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setCollapsible(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitleTextColor(ContextCompat.getColor(activity, R.color.colorSecondaryDark));


        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        return toolbar;
    }

    public AccountHeader getAccountHeader() {
        return accountHeader;
    }
}
