import { Paciente } from './../../../_model/paciente';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { PacienteService } from 'src/app/_service/paciente.service';

@Component({
  selector: 'app-paciente-edicion',
  templateUrl: './paciente-edicion.component.html',
  styleUrls: ['./paciente-edicion.component.css']
})
export class PacienteEdicionComponent implements OnInit {

  form: FormGroup;

  constructor(private pacienteService: PacienteService) { }

  ngOnInit() {
    this.form = new FormGroup({
      'id': new FormControl(0),
      'nombres': new FormControl(''),
      'apellidos': new FormControl(''),
      'dni': new FormControl(''),
      'telefono': new FormControl(''),
      'direccion': new FormControl(''),
      'email': new FormControl('')
    });

    this.pacienteService.listarPorId(1).subscribe(data => {
      this.form.value['id'] = data.values.name['idPaciente'];
      this.form.value['nombres'] = data.values.name['nombres'];
    });
  }

  submit() {
    let paciente = new Paciente();
    paciente.idPaciente = this.form.value['id'];
    paciente.nombres = this.form.value['nombres'];
    paciente.apellidos = this.form.value['apellidos'];
    paciente.dni = this.form.value['dni'];
    paciente.telefono = this.form.value['telefono'];
    paciente.direccion = this.form.value['direccion'];
    paciente.direccion = this.form.value['email'];

    this.pacienteService.registrar(paciente).subscribe(data => {
      console.log('paciente creado');
    });
  }

}
