import { environment } from './../environments/environment';
import { MaterialModule } from './material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { JwtModule } from '@auth0/angular-jwt';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PacienteComponent } from './pages/paciente/paciente.component';
import { MedicoComponent } from './pages/medico/medico.component';
import { HttpClientModule } from '@angular/common/http';
import { PacienteEdicionComponent } from './pages/paciente/paciente-edicion/paciente-edicion.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MedicoEdicionComponent } from './pages/medico/medico-edicion/medico-edicion.component';
import { ExamenComponent } from './pages/examen/examen.component';
import { ExamenEdicionComponent } from './pages/examen/examen-edicion/examen-edicion.component';
import { EspecialidadComponent } from './pages/especialidad/especialidad.component';
import { EspecialidadEdicionComponent } from './pages/especialidad/especialidad-edicion/especialidad-edicion.component';
import { ConsultaComponent } from './pages/consulta/consulta.component';
import { EspecialComponent } from './pages/consulta/especial/especial.component';
import { WizardComponent } from './pages/consulta/wizard/wizard.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BuscarComponent } from './pages/buscar/buscar.component';
import { BuscarDialogoComponent } from './pages/buscar/buscar-dialogo/buscar-dialogo.component';
import { ReporteComponent } from './pages/reporte/reporte.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { LoginComponent } from './pages/login/login.component';
import { Not404Component } from './pages/not404/not404.component';
import { Not403Component } from './pages/not403/not403.component';

export function tokenGetter(){
  let tk = sessionStorage.getItem(environment.TOKEN_NAME);
  return tk != null ? tk : '';
}

@NgModule({
  declarations: [
    AppComponent,
    PacienteComponent,
    MedicoComponent,
    PacienteEdicionComponent,
    MedicoEdicionComponent,
    ExamenComponent,
    ExamenEdicionComponent,
    EspecialidadComponent,
    EspecialidadEdicionComponent,
    ConsultaComponent,
    EspecialComponent,
    WizardComponent,
    BuscarComponent,
    BuscarDialogoComponent,
    ReporteComponent,
    LoginComponent,
    Not404Component,
    Not403Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule,
    PdfViewerModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:8080'],
        blacklistedRoutes: ['http://localhost:8080/login/enviarCorreo']
      }
    })
  ],
  entryComponents: [
    MedicoEdicionComponent, //esto porque va en un Dialogo, solo con Dialogos se hace esto
    BuscarDialogoComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
