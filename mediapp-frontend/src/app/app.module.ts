import { MaterialModule } from './material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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
    BuscarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule
  ],
  entryComponents: [
    MedicoEdicionComponent //esto porque va en un Dialogo, solo con Dialogos se hace esto
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
