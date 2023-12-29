import React from 'react'
import { useNavigate } from 'react-router-dom'

export default function MCart() {
    const navigator = new useNavigate()
    return (
        <div className="m-cart">
            <h3>DISCOVER</h3>
            <h2>₹ 99/ Per Month
            </h2>
            <ul>
                <li>5 classes per month</li>
                <li>4 group class monthly</li>
                <li>Online class access</li>
                <li>E-book fitness guide</li>
            </ul>
            <button onClick={()=>{
                navigator('/signup')
            }}>Choose Plan</button>
        </div>
    )
}
