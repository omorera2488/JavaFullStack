import { Paciente } from './../../../_model/paciente';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PacienteService } from 'src/app/_service/paciente.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-paciente-edicion',
  templateUrl: './paciente-edicion.component.html',
  styleUrls: ['./paciente-edicion.component.css']
})
export class PacienteEdicionComponent implements OnInit {

  form: FormGroup;
  id: number; // id del paciente en caso de edicion
  edicionFlag: boolean; // flag para activar modo edicion

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private pacienteService: PacienteService,
  ) { }

  ngOnInit() {
    this.form = new FormGroup({
      'id': new FormControl(0),
      'nombres': new FormControl('', [Validators.required, Validators.minLength(3)]),
      'apellidos': new FormControl('', Validators.required),
      'dni': new FormControl(''),
      'telefono': new FormControl(''),
      'direccion': new FormControl(''),
      'email': new FormControl('', Validators.email)
    });

    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.edicionFlag = params['id'] != null;
      this.initForm();
    });
  }

  get formCtrls(){return this.form.controls;}

  submit() {

    if(this.form.invalid){
      return;
    }

    let paciente = new Paciente();
    paciente.idPaciente = this.form.value['id'];
    paciente.nombres = this.form.value['nombres'];
    paciente.apellidos = this.form.value['apellidos'];
    paciente.dni = this.form.value['dni'];
    paciente.telefono = this.form.value['telefono'];
    paciente.direccion = this.form.value['direccion'];
    paciente.email = this.form.value['email'];

    if (this.edicionFlag) {
      this.pacienteService.modificar(paciente).subscribe(() =>{
        this.pacienteService.listar().subscribe(data =>{
          this.pacienteService.pacienteCambio.next(data);
          this.pacienteService.mensajeCambio.next("Paciente modificado");
        });
      });
    }else{
      this.pacienteService.registrar(paciente).subscribe(() =>{
        this.pacienteService.listar().subscribe(data =>{
          this.pacienteService.pacienteCambio.next(data);
          this.pacienteService.mensajeCambio.next("Paciente registrado");
        });
      });
    }
    this.router.navigate(['paciente']);
  }

  initForm() {
    if (this.edicionFlag) {
      this.pacienteService.listarPorId(this.id).subscribe(data => {
        this.form = new FormGroup({
          'id': new FormControl(data.idPaciente),
          'nombres': new FormControl(data.nombres),
          'apellidos': new FormControl(data.apellidos),
          'dni': new FormControl(data.dni),
          'telefono': new FormControl(data.telefono),
          'direccion': new FormControl(data.direccion),
          'email': new FormControl(data.email)
        });
      });
    }
  }

}
