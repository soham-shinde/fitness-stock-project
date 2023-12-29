import React from 'react'
import { useNavigate } from 'react-router-dom'

export default function MemCard({ data, uid }) {
    const navigate = useNavigate();
    return (

        <div className="membership-card">
            <img src={`./img/${data.type}.png`} alt="Membership 1" />
            <div>
                <h3>{data.name}</h3>
                <p className="price">₹ {data.price}/month</p>
                <p className="type">{data.type}</p>
                <p className="price">{data.duration} Months</p>
                <ul className="features" dangerouslySetInnerHTML={{ __html: `<li>${data.features.replace(/\n/g, '</li><li>')}</li>` }}>

                </ul>
                <button className="choose-plan-button" onClick={
                    () => {
                       
                        navigate(`/payment-page/${uid}/membership/${data.id}`)
                        
                    }
                }>Choose Plan</button>
            </div>
        </div>


    )
}
