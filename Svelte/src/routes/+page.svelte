<script lang="ts">
	import { tweened } from 'svelte/motion';
	import { fade } from 'svelte/transition';

	let standing: boolean = true;
	let angle = tweened(0);

	function rotateStephen() {
		standing = !standing;
		angle.set(standing ? 0 : -90);
	}
</script>

{#if !standing}
	<h1 transition:fade>стивен вольфрам упал))</h1>
{/if}
<img src="/wolfram.jpg" width="30%" alt="Stephen Wolfram" style="transform: rotate({$angle}deg);" />
<br />
<button on:click={rotateStephen}>{standing ? 'упасть' : 'встать'} хаха</button>

<style lang="scss">
	img,
	button {
		display: block;
		margin-left: auto;
		margin-right: auto;
	}

	img {
		margin-top: 0px;
		margin-bottom: 0px;
	}

	h1 {
		text-align: center;
		font-size: 3vw;
		color: blue;
		background-color: orange;
		text-decoration: underline wavy;
		text-decoration-color: red;
	}

	button {
		font-size: 5vw;
	}
</style>
