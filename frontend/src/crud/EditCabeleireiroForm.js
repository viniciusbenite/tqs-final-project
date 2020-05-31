import React, { useState, useEffect } from 'react'
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import api from "../services/api";

const EditCabeleireiroForm = props => {
  const [ cabeleireiro, setCabeleireiro ] = useState(props.currentCabeleireiro)

  const [services, setServices] = React.useState({
    cortes: false,
    manicure_pedicure: false,
    escovas: false,
    tratamento_capilar: false,
    serviços_especiais: false,
    depilacao: false,
    maquilhagem: false,
    quimicas: false,
  });
  const [hours, setHours] = React.useState({
    cortes: [],
    manicure_pedicure: [],
    escovas: [],
    tratamento_capilar: [],
    serviços_especiais: [],
    depilacao: [],
    maquilhagem: [],
    quimicas: [],
  });

  useEffect(
    () => {
      setCabeleireiro(props.currentCabeleireiro)
    },
    [ props ]
  )
  // You can tell React to skip applying an effect if certain values haven’t changed between re-renders. [ props ]

  const handleToggle = ({ target }) =>
      setServices(s => ({ ...s, [target.name]: !s[target.name] }));

  const handleHourChange = event => {
    const { name, value } = event.target
    setHours({ ...hours, [name]: value.split(",")  });
  }

  const handleInputChange = event => {
    const { name, value } = event.target;
    setCabeleireiro({ ...cabeleireiro, [name]: value })
  }

  return (
    <form
      onSubmit={event => {
        event.preventDefault()

        console.log(cabeleireiro)
        api.get("/user/").then(function (response) {
          let data = response.data;
          let res;
          for (const i in data) {
            for (const n in data[i]) {
              if (data[i].email === localStorage.getItem("user_email")) {
                res = data[i];
              }
            }
          }
          props.updateCabeleireiro(cabeleireiro.id, cabeleireiro)
          api.put("/saloon/" + cabeleireiro.id, {name: cabeleireiro.name,postalCode: cabeleireiro.postalCode, city: cabeleireiro.city ,country: cabeleireiro.country, status: cabeleireiro.status, type: cabeleireiro.type, contact: cabeleireiro.contact, description: cabeleireiro.description
            ,address: cabeleireiro.address, owner: res}).then();
        })
      }}
    >
      <label>Nome</label>
      <input type="text" name="name" value={cabeleireiro.name} onChange={handleInputChange} />
      <label>Postal Code</label>
      <input type="text" name="postalCode" value={cabeleireiro.postalCode} onChange={handleInputChange} />
      <label>Cidade</label>
      <input type="text" name="city" value={cabeleireiro.city} onChange={handleInputChange} />
      <label>Country</label>
      <input type="text" name="country" value={cabeleireiro.country} onChange={handleInputChange} />
      <label>Status</label>
      <input type="text" name="status" value={cabeleireiro.status} onChange={handleInputChange} />
      <label>Type</label>
      <input type="text" name="type" value={cabeleireiro.type} onChange={handleInputChange} />
      <label>Contact</label>
      <input type="text" name="contact" value={cabeleireiro.contact} onChange={handleInputChange} />
      <label>Description</label>
      <input type="text" name="description" value={cabeleireiro.description} onChange={handleInputChange} />
      <label>Address</label>
      <input type="text" name="address" value={cabeleireiro.address} onChange={handleInputChange} />
      <label>Pick your services</label>
      <FormGroup>
        <FormControlLabel
            control={<Checkbox checked={services.cortes} onChange={handleToggle} name="cortes" />}
            label="Cortes"
        />
        <input type="text" name="cortes" value={hours.cortes} onChange={handleHourChange}  placeholder="Type your available schedules(09:00:00 P ex.)"/>
        <FormControlLabel
            control={<Checkbox checked={services.manicure_pedicure} onChange={handleToggle} name="manicure_pedicure" />}
            label="Manicure Pedicure"
        />
        <input type="text" name="manicure_pedicure" value={hours.manicure_pedicure} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
        <FormControlLabel
            control={<Checkbox checked={services.escovas} onChange={handleToggle} name="escovas" />}
            label="Escovas"
        />
        <input type="text" name="escovas" value={hours.escovas} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
        <FormControlLabel
            control={<Checkbox checked={services.tratamento_capilar} onChange={handleToggle} name="tratamento_capilar" />}
            label="Tratamento Capilar"
        />
        <input type="text" name="tratamento_capilar" value={hours.tratamento_capilar} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
        <FormControlLabel
            control={<Checkbox checked={services.serviços_especiais} onChange={handleToggle} name="serviços_especiais" />}
            label="Serviços Especiais"
        />
        <input type="text" name="serviços_especiais" value={hours.serviços_especiais} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
        <FormControlLabel
            control={<Checkbox checked={services.depilacao} onChange={handleToggle} name="depilacao" />}
            label="Depilação"
        />
        <input type="text" name="depilacao" value={hours.depilacao} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
        <FormControlLabel
            control={<Checkbox checked={services.maquilhagem} onChange={handleToggle} name="maquilhagem" />}
            label="Maquilhagem"
        />
        <input type="text" name="maquilhagem" value={hours.maquilhagem} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
        <FormControlLabel
            control={<Checkbox checked={services.quimicas} onChange={handleToggle} name="quimicas" />}
            label="Quimicas"
        />
        <input type="text" name="quimicas" value={hours.quimicas} onChange={handleHourChange} placeholder="Type your available schedules(09:00:00 P ex.)" />
      </FormGroup>
      <button>Editar cabeleireiro</button>
    </form>
  )
}

export default EditCabeleireiroForm
